package ru.androidschool.rxjava.kotlin.operators

import io.reactivex.rxjava3.core.Observable
import org.junit.Test

class Take {

    @Test
    fun takeDemo() {
        val source = Observable.fromArray(1, 2, 3, 4)
        source.take(2).subscribe { s -> println(s) }
    }
}