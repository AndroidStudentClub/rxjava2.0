package ru.androidschool.rxjava.kotlin.create

import io.reactivex.Observable
import org.junit.Test

class JustVsFromCallble {

    @Test
    fun justDemo() {
        val source = Observable.just(System.currentTimeMillis())
        source.subscribe { s -> println("RECEIVED: $s") }
        println("Sleeping 5 s: ")
        sleep(5000)
        source.subscribe { s -> println("RECEIVED: $s") }
    }

    @Test
    fun fromCallableDemoSample() {

        val source = Observable.fromCallable { System.currentTimeMillis().toString() }

        source.subscribe { s -> println("RECEIVED: $s") }
        println("Sleeping 5 s: ")
        sleep(5000)
        source.subscribe { s -> println("RECEIVED: $s") }
    }

    companion object {

        fun sleep(millis: Long) {
            try {
                Thread.sleep(millis)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

        }
    }
}
