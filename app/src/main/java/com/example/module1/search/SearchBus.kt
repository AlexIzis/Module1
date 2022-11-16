package com.example.module1.search

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject


class SearchBus {
    companion object {
        private val publisher: PublishSubject<String> = PublishSubject.create()

        private var mInstance: SearchBus? = null
        val instance: SearchBus?
            get() {
                if (mInstance == null) {
                    mInstance = SearchBus()
                }
                return mInstance
            }
        fun publish(event: String?) {
            if (event != null) {
                publisher.onNext(event)
            }
        }

        fun listen(): Observable<String> {
            return publisher
        }
    }
}