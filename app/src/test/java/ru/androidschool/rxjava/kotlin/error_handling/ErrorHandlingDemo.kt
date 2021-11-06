package ru.androidschool.rxjava.kotlin.error_handling

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.observers.TestObserver
import org.junit.Test

class ErrorHandlingDemo {

    @Test
    fun withoutErrorHandling() {
        val testObserver = TestObserver<Int>()

        val source = Observable.just(5, 0, 1)
            .map { i -> 10 / i }

        source.subscribe(testObserver)
        testObserver.assertNoErrors()
    }

    @Test
    fun whenHandleOnErrorReturnItem() {
        val testObserver = TestObserver<Int>()

        val source = Observable.just(5, 0, 1)
            .map { i -> 10 / i }
            .onErrorReturnItem(200)

        source.subscribe(testObserver)
        testObserver.assertNoErrors()
        testObserver.assertComplete()
        testObserver.assertValueCount(2)
        testObserver.assertValues(2, 200)
    }

    @Test
    fun whenHandleOnErrorResumeNext_thenResumed() {
        val testObserver = TestObserver<Int>()

        val source = Observable.just(5, 2, 1, 0, 3, 2, 8)
            .map { i -> 10 / i }
            .onErrorResumeWith(Observable.just(100, 200, 300))

        source.subscribe(testObserver)

        testObserver.assertNoErrors()
        testObserver.assertComplete()
        testObserver.assertValueCount(6)
        testObserver.assertValues(2, 5, 10, 100, 200, 300)
    }

    @Test
    fun retryDemo() {
        Observable.just(5, 2, 4, 0, 3, 2, 8)
            .map { i -> 10 / i }
            .retry(1)
            .subscribe({ i -> println("RECEIVED: " + i!!) },
                { e -> println("RECEIVED ERROR: $e") }
            )
    }
}