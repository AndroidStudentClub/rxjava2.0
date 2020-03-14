package ru.androidschool.rxjava.kotlin.operators

import io.reactivex.Observable
import org.junit.Test
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

class MapExample {

    @Test
    fun mapDemo() {
        // Создаём объект DTO класса
        val bookDto = BookDto(title = "Война и мир", author = "Лев Толстой", size = 1024, price = 1700.0)

        // Создаём Observable
        val source = Observable.just(bookDto)

        source
            // Применяем опертор map для конвертации DTO -> VO
            .map { dto ->
                BookConverter.toViewObject(dto)
            }
            // Подписываемся на получение данных
            .subscribe { s -> println(s) }
    }
}

data class BookDto(
    val title: String,
    val author: String,
    val size: Long,
    val price: Double
)

data class BookVo(
    val title: String,
    val author: String,
    val size: Long,
    val price: String
)

object BookConverter {

    fun toViewObject(dto: BookDto): BookVo {
        return BookVo(
            title = dto.title,
            author = dto.author,
            size = dto.size / 1024,
            price = formatPrice(dto.price).plus("₽")
        )
    }
}


fun formatPrice(price: Double): String {
    val separator = DecimalFormatSymbols().apply {
        groupingSeparator = ' '
        decimalSeparator = ','
    }
    return DecimalFormat("###,##0.00", separator).format(price).replace(",00", "")
}