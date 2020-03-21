package ru.mikhailskiy.retrofitexample

import android.os.Bundle
import android.util.Log
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.mikhailskiy.retrofitexample.adapters.MoviesAdapter
import ru.mikhailskiy.retrofitexample.data.MoviesResponse
import ru.mikhailskiy.retrofitexample.network.MovieApiClient

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Добавляем recyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.movies_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val call = MovieApiClient.apiClient.getTopRatedMovies(API_KEY, "ru")

        call.enqueue(object : Callback<MoviesResponse> {
            override fun onResponse(
                call: Call<MoviesResponse>, response: Response<MoviesResponse>
            ) {
                val movies = response.body()!!.results
                // Передаем результат в adapter и отображаем элементы
                recyclerView.adapter = MoviesAdapter(movies, R.layout.list_item_movie)
            }

            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                // Логируем ошибку
                Log.e(TAG, t.toString())
            }
        })
    }

    companion object {

        private val TAG = MainActivity::class.java.simpleName

        // TODO - insert your themoviedb.org API KEY here
        private val API_KEY = ""
    }
}

