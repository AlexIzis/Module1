package com.example.module1.search

import android.support.annotation.MainThread
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject


class SearchBus {
    companion object {
        private val publisher: PublishSubject<String> = PublishSubject.create()
        private var newInstance: SearchBus? = null
        val instance: SearchBus?
            get() {
                if (newInstance == null) {
                    instance?.let {
                        synchronized(it) {
                            if (newInstance == null) {
                                newInstance = SearchBus()
                            }
                        }
                    }
                }
                return newInstance
            }

        @MainThread
        fun publish(event: String) {
            publisher.onNext(event)
        }

        fun listen(): Observable<String> {
            return publisher
        }
    }
}