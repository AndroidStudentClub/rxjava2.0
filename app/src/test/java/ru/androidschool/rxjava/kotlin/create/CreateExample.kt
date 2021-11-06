package ru.androidschool.rxjava.kotlin.create


import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableOnSubscribe
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import org.junit.Test

class CreateExample {

    @Test
    fun createDemoSample() {

        val source = Observable.create(ObservableOnSubscribe<String> { emitter ->
            emitter.onNext("Java")
            emitter.onNext("Kotlin")
            emitter.onNext("Go")
            emitter.onNext("Cpp")
            emitter.onNext("Python")
            emitter.onComplete()
        })

        source.subscribe(object : Observer<String> {
            var d: Disposable? = null

            override fun onSubscribe(d: Disposable) {
                this.d = d
                println("onSubscribe: " + Thread.currentThread().name)
            }

            override fun onNext(s: String) {

                println("onNext: $s")
            }

            override fun onError(e: Throwable) {
                println("onError: $e")
            }

            override fun onComplete() {
                println("onComplete")
            }
        })
    }
}