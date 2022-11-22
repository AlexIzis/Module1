package com.example.module1.news

import android.support.annotation.MainThread
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject

class NewsBus {
    companion object {
        private var readNews = arrayListOf<String>()
        private val publisher: PublishSubject<String> = PublishSubject.create()
        private var currentInstance: NewsBus? = null
        val instance: NewsBus?
            get() {
                if (currentInstance == null) {
                    instance?.let {
                        synchronized(it) {
                            if (currentInstance == null) {
                                currentInstance = NewsBus()
                            }
                        }
                    }
                }
                return currentInstance
            }

        @MainThread
        fun publish(id: String) {
            if (!readNews.contains(id)) {
                readNews.add(id)
            }
            publisher.onNext(readNews.size.toString())
        }

        fun listen(): Observable<String> {
            return publisher
        }
    }
}