package ru.androidschool.rxjava;

import org.junit.Test;

import java.util.concurrent.Callable;

import io.reactivex.Observable;

public class CreateObservable {

    @Test
    public void justDemoSample() {
        Observable<String> source = Observable.just("Hello");
        source.subscribe(s -> System.out.println("RECEIVED: " + s));
    }

    @Test
    public void fromCallableDemoSample() {

        Observable<String> source =
                Observable.fromCallable(new Callable<String>() {
                    @Override
                    public String call() throws Exception {
                        return Long.toString(System.currentTimeMillis());
                    }
                });

        source.subscribe(s -> System.out.println("RECEIVED: " + s));
    }

}
