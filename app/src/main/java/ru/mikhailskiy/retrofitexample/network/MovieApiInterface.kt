package ru.mikhailskiy.retrofitexample.network


import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import ru.mikhailskiy.retrofitexample.data.MoviesResponse

interface MovieApiInterface {

    @GET("movie/top_rated")
    fun getTopRatedMovies(@Query("api_key") apiKey: String, @Query("language") language: String): Single<MoviesResponse>
}
