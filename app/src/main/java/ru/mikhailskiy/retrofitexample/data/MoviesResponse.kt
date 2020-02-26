package ru.mikhailskiy.retrofitexample.data

import com.google.gson.annotations.SerializedName

data class MoviesResponse (
    var page: Int,
    var results: List<Movie>,
    @SerializedName("total_results")
    var totalResults: Int,
    @SerializedName("total_pages")
    var totalPages: Int
)



