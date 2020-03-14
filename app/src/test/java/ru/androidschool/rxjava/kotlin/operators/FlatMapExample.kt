package ru.androidschool.rxjava.kotlin.operators

import io.reactivex.Observable
import org.junit.Test

class FlatMapExample {

    @Test
    fun flatMapDemo() {
        val initialSource: Observable<String> = Observable.just("day")

        initialSource.flatMap {
            if (it == "day") {
                return@flatMap Observable.just("Monday", "Tuesday", "Sunday")
            } else {
                return@flatMap Observable.just("March", "April", "June")
            }
        }
            // Подписываемся чтобы получить данные
            .subscribe { s -> println(s) }
    }


}