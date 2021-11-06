package ru.androidschool.rxjava.kotlin.subjects

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.Subject
import io.reactivex.rxjava3.subjects.UnicastSubject
import org.junit.Test
import java.util.concurrent.TimeUnit

class UnicastSubject {

    @Test
    fun unicastcSubjectdDemo() {
        val subject: Subject<String> = UnicastSubject.create()
        
        Observable.interval(300, TimeUnit.MILLISECONDS)
            .map { l: Long -> ((l + 1) * 300).toString() + " milliseconds" }
            .subscribe(subject)

        sleep(2000)
        subject.subscribe { s -> println("Observer 1: $s") }
        sleep(2000)
    }

    fun sleep(millis: Long) {
        try {
            Thread.sleep(millis)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

}