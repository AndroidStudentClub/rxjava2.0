package ru.androidschool.rxjava.kotlin.subjects

import io.reactivex.subjects.ReplaySubject
import io.reactivex.subjects.Subject
import org.junit.Test

class ReplaySubjectDemo {

    @Test
    fun replaySubjectdDemo() {
        val subject: Subject<String> = ReplaySubject.create()

        subject.subscribe { s -> println("Observer 1: $s") }

        subject.onNext("Alpha")
        subject.onNext("Beta")
        subject.onNext("Gamma")

        subject.onComplete()
        // Здесь подписываемся еще раз и получаем все переданные события
        subject.subscribe { s -> println("Observer 2: $s") }
    }
}