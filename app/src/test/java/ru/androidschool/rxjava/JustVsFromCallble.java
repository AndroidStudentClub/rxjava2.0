package ru.androidschool.rxjava;

import org.junit.Test;
import java.util.concurrent.Callable;

import io.reactivex.rxjava3.core.Observable;

public class JustVsFromCallble {

    @Test
    public void justDemo() {
        Observable<Long> source = Observable.just(System.currentTimeMillis());
        source.subscribe(s -> System.out.println("RECEIVED: " + s));
        System.out.println("Sleeping 5 s: ");
        sleep(5000);
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
        System.out.println("Sleeping 5 s: ");
        sleep(5000);
        source.subscribe(s -> System.out.println("RECEIVED: " + s));
    }

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

