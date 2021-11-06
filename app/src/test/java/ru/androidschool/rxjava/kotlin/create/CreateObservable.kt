package ru.androidschool.rxjava.kotlin.create

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import org.junit.Test


class CreateObservable {

    @Test
    fun justDemoSample() {
        val source = Observable.just("Hello")
        source.subscribe { s -> println("RECEIVED: $s") }
    }

    @Test
    fun fromCallableDemoSample() {
        val source = Observable.fromCallable { System.currentTimeMillis().toString() }
        source.subscribe { s -> println("RECEIVED: $s") }
    }

    @Test
    fun justSingleDemoSample() {
        val source = Single.just("Hello")
        source.subscribe { s -> println("RECEIVED: $s") }
    }

}
