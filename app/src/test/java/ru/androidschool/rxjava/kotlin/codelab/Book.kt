package ru.androidschool.rxjava.kotlin.codelab

data class Book(
    val title: String,
    val author: String,
    val id: Long?,
    val price: Double,
    val location: String,
    val currency: String
)