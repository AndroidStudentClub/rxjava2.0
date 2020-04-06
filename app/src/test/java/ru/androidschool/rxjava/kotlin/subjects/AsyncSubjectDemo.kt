package ru.androidschool.rxjava.kotlin.subjects

import io.reactivex.subjects.AsyncSubject
import io.reactivex.subjects.Subject
import org.junit.Test

class AsyncSubjectDemo {

    @Test
    fun asyncSubjectdDemo() {
        val subject: Subject<String> = AsyncSubject.create()

        subject.subscribe(
            { s -> println("Observer 1: $s") },
            { e -> e.printStackTrace() }
        ) { println("Observer 1 done!") }

        subject.onNext("Alpha")
        subject.onNext("Beta")
        subject.onNext("Gamma")
        subject.onComplete()

        subject.subscribe(
            { s -> println("Observer 2: $s") },
            { e: Throwable -> e.printStackTrace() }
        ) { println("Observer 2 done!") }
    }
}