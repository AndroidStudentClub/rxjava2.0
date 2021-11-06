package ru.androidschool.rxjava.kotlin.create

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import org.junit.Test

class DisposableExample {

    @Test
    fun disposableDemoSample() {
        var disposable: Disposable? = null
        val source = Observable.just("Hello")
        disposable = source.subscribe { s -> println("RECEIVED: $s") }
        // Очищаем ресуры
        disposable.dispose()
    }

    @Test
    fun compositeDisposableDemoSample() {
        val compositeDisposable = CompositeDisposable()
        var disposable: Disposable? = null
        val source = Observable.just("Hello")
        compositeDisposable.addAll(source.subscribe { s -> println("RECEIVED: $s") })
        // Очищаем ресуры
        compositeDisposable.clear()
    }
}