package ru.androidschool.rxjava.kotlin.operators

import io.reactivex.rxjava3.core.Observable
import org.junit.Test
import ru.androidschool.rxjava.JustVsFromCallble.sleep
import java.util.concurrent.TimeUnit


class FilteringOperators {

    @Test
    fun filtertDemoWithLength() {
        val source = Observable.fromArray("Java", "Go", "C++", "Kotlin", "PHP")
        source.filter { x -> x.length > 4 }.subscribe { s -> println(s) }
    }

    @Test
    fun filtertDemoDivide() {
        val source = Observable.fromArray(15, 21, 34, 56, 108)
        source.filter { x -> x / 7 == 3 }.subscribe { s -> println(s) }
    }

    @Test
    fun takeDemo() {
        val source = Observable.fromArray(1, 2, 3, 4)
        source.take(2).subscribe { s -> println(s) }
    }

    @Test
    fun skipDemo() {
        val source = Observable.fromArray(1, 2, 3, 4)
        source.skip(2).subscribe { s -> println(s) }
    }

    @Test
    fun takeWhile() {
        val source = Observable.fromArray(1, 2, 3, 4, 5, 1, 0)
        source.takeWhile { x -> x < 4 }.subscribe { s -> println(s) }
    }

    @Test
    fun skipWhile() {
        val source = Observable.fromArray(1, 2, 3, 4, 5, 1, 0)
        source.skipWhile { x -> x < 4 }.subscribe { s -> println(s) }
    }

    @Test
    fun skipWhileStrings() {
        val source =
            Observable.fromArray("C++", "Go", "Java", "Kotlin", "C++", "C#", "PHP", "JavaScript")
        source.skipWhile { x -> x.length < 4 }.subscribe { s -> println(s) }
    }

    @Test
    fun takeWhileStrings() {
        val source = Observable.fromArray("C#", "Go", "Java", "Kotlin", "C++", "PHP", "JavaScript")
        source.takeWhile { x -> x.length == 2 }.subscribe { s -> println(s) }
    }

    @Test
    fun distinctDemo() {
        val source = Observable.fromArray(5, 4, 5, 6, 7, 2, 3, 4, 5, 1, 8, 9, 5)
        source.distinct().subscribe { s -> println(s) }
    }

    @Test
    fun elementAtDemo() {
        val source = Observable.fromArray(1, 2, 3, 4, 5)
        source.elementAt(2).subscribe { s -> println(s) }
    }

    @Test
    fun distinctLessonDemo() {
        val source = Observable.fromArray(
            "C#",
            "Go",
            "Java",
            "C#",
            "Kotlin",
            "C#",
            "Kotlin",
            "C++",
            "PHP",
            "JavaScript"
        )
        source.distinct().subscribe { s -> println(s) }
    }

    @Test
    fun elementAtLessonDemo() {
        val source = Observable.fromArray("Stepic", "Udacity", "Coursera", "AndroidSchool.ru")
        source.elementAt(3).subscribe { s -> println(s) }
    }

    @Test
    fun debounceDemo() {

        val source1 =
            Observable.interval(50, TimeUnit.MILLISECONDS)
                .map { i: Long -> "Red $i" } // map to elapsed time
                .take(3)
                .doOnNext { x: String? -> println(x) }

        val source2 =
            Observable.interval(260, TimeUnit.MILLISECONDS)
                .map { i: Long -> "Green $i" } // map to elapsed time
                .take(3)
                .doOnNext { x: String? -> println(x) }

        val source3 =
            Observable.interval(151, TimeUnit.MILLISECONDS)
                .map { i: Long -> "Blue $i" } // map to elapsed time
                .take(2)
                .doOnNext { x: String? -> println(x) }

        Observable.merge(source1, source2, source3)
            .debounce(100, TimeUnit.MILLISECONDS)
            .subscribe { x: String? -> println("Result $x") }

        sleep(5000)
    }
}