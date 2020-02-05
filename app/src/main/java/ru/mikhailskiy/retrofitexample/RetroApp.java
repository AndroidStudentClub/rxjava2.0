package ru.mikhailskiy.retrofitexample;

import android.app.Application;

import retrofit2.Retrofit;
import ru.mikhailskiy.retrofitexample.network.AdvancedApiClient;
import ru.mikhailskiy.retrofitexample.network.ApiInterface;

public class RetroApp extends Application {
    public ApiInterface service;

    private static RetroApp instance;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        initRetrofit();
    }

    public static RetroApp getInstance() {
        return instance;
    }

    private void initRetrofit() {

        Retrofit retrofit = AdvancedApiClient.getClient();

        service = retrofit.create(ApiInterface.class);
    }
}



