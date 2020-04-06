package ru.androidschool.rxjava.kotlin.subjects

import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import org.junit.Test

class PublishSubjectDemo {

    @Test
    fun publishSubjectDemo() {
        val subject: Subject<String> = PublishSubject.create()

        subject.subscribe { x -> println(x) }

        subject.onNext("1")
        subject.onNext("2")
        subject.onNext("3")

        subject.onComplete()
    }
}