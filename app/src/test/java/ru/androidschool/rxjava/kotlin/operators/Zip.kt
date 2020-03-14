package ru.androidschool.rxjava.kotlin.operators

import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Function3
import org.junit.Test

class Zip {

    @Test
    fun zipDemo() {
        // Имитация запроса на получение имени пользователя
        val source1 = Observable.just(User("John", "Wick"))
        // Источник данных для получения данных пасспорта
        val source2 = Observable.just(UserPassport("2.09.1964", "New York"))

        // Объединяем данные
        Observable.zip(source1, source2,
            // Используется для объединения данных
            BiFunction<User, UserPassport, UserData> { user, passport ->
                UserData(user.name, passport.date, passport.address)
            })
            // Подписываемся чтобы получить данные
            .subscribe { s -> println(s) }
    }

    @Test
    fun zipLessonDemo() {

        val source1 = Observable.fromArray(1, 2, 3)
        val source2 = Observable.fromArray("A", "B")

        Observable.zip(source1, source2,
            BiFunction<Int, String, String> { number, string ->
                string + number
            }).subscribe { s -> println(s) }
    }
}

data class User(
    val name: String,
    val surname: String
)

data class UserPassport(
    val date: String,
    val address: String
)

data class UserData(
    val name: String,
    val date: String,
    val address: String
)