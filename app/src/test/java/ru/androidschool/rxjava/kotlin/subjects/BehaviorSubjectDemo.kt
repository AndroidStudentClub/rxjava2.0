package ru.androidschool.rxjava.kotlin.subjects

import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.Subject
import org.junit.Test

class BehaviorSubjectDemo {

    @Test
    fun behaviorSubjectdDemo() {
        val subject: Subject<String> = BehaviorSubject.create()

        subject.subscribe { s -> println("Observer 1: $s") }

        subject.onNext("Alpha")
        subject.onNext("Beta")
        subject.onNext("Gamma")

        subject.subscribe { s -> println("Observer 2: $s") }
    }
}