package ru.mikhailskiy.retrofitexample

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import ru.mikhailskiy.retrofitexample.adapters.MoviesAdapter
import ru.mikhailskiy.retrofitexample.data.Movie
import ru.mikhailskiy.retrofitexample.network.MovieApiClient
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        search_toolbar
            .onTextChangedObservable
            .map { it.trim() }
            .doOnNext { Log.d("THR# 38", Thread.currentThread().name) }
            .debounce(500, TimeUnit.MILLISECONDS)
            .doOnNext { Log.d("THR# 40", Thread.currentThread().name) }
            .filter { it.isNotEmpty() }
            .doOnNext { Log.d("THR# 42", Thread.currentThread().name) }
            .observeOn(Schedulers.io())
            .doOnNext { Log.d("THR# 44", Thread.currentThread().name) }
            .flatMapSingle { it -> MovieApiClient.apiClient.searchByQuery(MainActivity.API_KEY, "ru", it) }
            .doOnNext { Log.d("THR# After flatMap ", Thread.currentThread().name) }
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext { Log.d("THR# after AnSchedulers", Thread.currentThread().name) }
            .subscribe({
                setMovies(it.results)
                Log.d(MainActivity.TAG, it.toString())
            }, {
                Log.e(MainActivity.TAG, it.toString())
            })


        // Получаем Single
        val getTopRatedMovies = MovieApiClient.apiClient.getTopRatedMovies(API_KEY, "ru")

        getTopRatedMovies
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    val movies = it.results
                    // Передаем результат в adapter и отображаем элементы
                    movies_recycler_view.adapter = MoviesAdapter(movies, R.layout.list_item_movie)
                },
                { error ->
                    // Логируем ошибку
                    Log.e(TAG, error.toString())
                }
            )
    }

    fun setMovies(movies: List<Movie>) {
        movies_recycler_view.adapter = MoviesAdapter(movies, R.layout.list_item_movie)
    }

    companion object {

        private val TAG = MainActivity::class.java.simpleName

        // TODO - insert your themoviedb.org API KEY here
        private val API_KEY = ""
    }
}
