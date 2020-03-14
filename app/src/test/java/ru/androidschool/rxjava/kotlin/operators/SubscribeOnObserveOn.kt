package ru.androidschool.rxjava.kotlin.operators

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.junit.Test
import ru.androidschool.rxjava.JustVsFromCallble.sleep
import java.util.concurrent.TimeUnit

class SubscribeOnObserveOn {

    @Test
    fun subscribeOnDemo() {

        val source = Observable.just(1, 2)
        source
            .subscribeOn(Schedulers.computation())
            .subscribe { i ->
                println("Received " + i + " on thread " + Thread.currentThread().name)
            }

        sleep(5000)
    }


    @Test
    fun observeOnDemo() {

        val source = Observable.just(1, 2)
        source
            .subscribeOn(Schedulers.computation())
            .observeOn(Schedulers.newThread())
            .subscribe { i ->
                println("Received " + i + " on thread " + Thread.currentThread().name)
            }

        sleep(5000)
    }
}