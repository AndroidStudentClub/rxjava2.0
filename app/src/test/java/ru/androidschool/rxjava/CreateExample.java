package ru.androidschool.rxjava;

import org.junit.Test;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;

public class CreateExample {

    @Test
    public void createDemoSample() {

        Observable<String> source = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) {
                emitter.onNext("Java");
                emitter.onNext("Kotlin");
                emitter.onNext("Go");
                emitter.onNext("Cpp");
                emitter.onNext("Python");
                emitter.onComplete();
            }
        });

        source.subscribe(new Observer<String>() {
            Disposable d;

            @Override
            public void onSubscribe(Disposable d) {
                this.d = d;
                System.out.println("onSubscribe: " + Thread.currentThread().getName());
            }

            @Override
            public void onNext(String s) {

                System.out.println("onNext: " + s);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError: " + e);
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        });
    }
}
