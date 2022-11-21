package com.example.module1.search

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized


class SearchBus {
    companion object {
        private val publisher: PublishSubject<String> = PublishSubject.create()
        private var Instance: SearchBus? = null
        @OptIn(InternalCoroutinesApi::class)
        val instance: SearchBus?
            get() {
                if (Instance == null) {
                    instance?.let {
                        synchronized(it) {
                            if (Instance == null) {
                                Instance = SearchBus()
                            }
                        }
                    }
                }
                return Instance
            }

        fun publish(event: String) {
            publisher.onNext(event)
        }

        fun listen(): Observable<String> {
            return publisher
        }
    }
}