package ru.mikhailskiy.retrofitexample

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.mikhailskiy.retrofitexample.adapters.MoviesAdapter
import ru.mikhailskiy.retrofitexample.data.MoviesResponse
import ru.mikhailskiy.retrofitexample.network.MovieApiClient

class MainActivity : AppCompatActivity() {

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Добавляем recyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.movies_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Получаем Single
        val getTopRatedMovies = MovieApiClient.apiClient.getTopRatedMovies(API_KEY, "ru")

        getTopRatedMovies
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { it ->
                    val movies = it.results
                    // Передаем результат в adapter и отображаем элементы
                    recyclerView.adapter = MoviesAdapter(movies, R.layout.list_item_movie)
                },
                { error ->
                    // Логируем ошибку
                    Log.e(TAG, error.toString())
                }
            )
    }

    companion object {

        private val TAG = MainActivity::class.java.simpleName

        // TODO - insert your themoviedb.org API KEY here
        private val API_KEY = "0bd95c30f721d1e94381142dc1ce3d50"
        // private val API_KEY = "7e8f60e325cd06e164799af1e317d7a7"
    }
}

