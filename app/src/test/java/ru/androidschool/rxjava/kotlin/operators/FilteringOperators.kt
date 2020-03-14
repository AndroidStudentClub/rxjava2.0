package ru.androidschool.rxjava.kotlin.operators

import io.reactivex.Observable
import org.junit.Test

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

}