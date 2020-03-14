package ru.androidschool.rxjava.kotlin.operators

import io.reactivex.Observable
import org.junit.Test
import ru.androidschool.rxjava.JustVsFromCallble.sleep
import java.util.concurrent.TimeUnit

class Merging {

    @Test
    fun mergeDemo() {

        // Излучает данные каждую секунду, но берем только 2
        val source1 = Observable.interval(1, TimeUnit.SECONDS)
            .take(2)
            .map { l -> l + 1 } // emit elapsed seconds
            .map { l -> "Источник 1: $l секунд" }

        // Излучает данные каждые 300 миллисекунд
        val source2 = Observable.interval(300, TimeUnit.MILLISECONDS)
            .map { l -> (l + 1) * 300 } // emit elapsed milliseconds
            .map { l -> "Источник 2: $l миллисекунд" }

        // Здесь применяем оператор merge()
        Observable.merge(source1, source2)
            .subscribe { i -> println("Получено: $i") }

        //Подождем 5 секунд - чтобы метод не завершился раньше времени
        sleep(5000)
    }

    @Test
    fun concatDemo() {

        // Излучает данные каждую секунду, но берем только 2
        val source1 = Observable.interval(1, TimeUnit.SECONDS)
            .take(2)
            .map { l -> l + 1 } // emit elapsed seconds
            .map { l -> "Источник 1: $l секунд" }

        // Излучает данные каждые 300 миллисекунд
        val source2 = Observable.interval(300, TimeUnit.MILLISECONDS)
            .map { l -> (l + 1) * 300 } // emit elapsed milliseconds
            .map { l -> "Источник 2: $l миллисекунд" }

        // Здесь применяем оператор concat()
        Observable.concat(source1, source2)
            .subscribe { i -> println("Получено: $i") }

        //Подождем 5 секунд - чтобы метод не завершился раньше времени
        sleep(5000)
    }
}