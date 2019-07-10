package com.example.hystrix.hellohystrix;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixObservableCommand;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

public class HelloObservableCommand extends HystrixObservableCommand<String> {
    private final String name;

    public HelloObservableCommand(String name) {
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
        this.name = name;
    }

    @Override
    protected Observable<String> construct() {
        return Observable.create((Observable.OnSubscribe<String>) subscriber -> {
            try {
                subscriber.onNext("Hello ");
                subscriber.onNext(HelloObservableCommand.this.name + "!");
                subscriber.onCompleted();
            } catch(Exception e){
                subscriber.onError(e);
            }
        }).subscribeOn(Schedulers.io());
    }
}