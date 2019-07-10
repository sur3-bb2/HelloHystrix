package com.example.hystrix.hellohystrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@SpringBootApplication
public class HellohystrixApplication {

    public static void main(String[] args) {
        SpringApplication.run(HellohystrixApplication.class, args);

        System.out.println("-----------------------------------");

        //callHelloCommand();
        callObservable();

        System.out.println("-----------------------------------");
    }

    static void callHelloCommand() {
        System.out.println(new HelloCommand("suresh").execute());

        Future<String> s = new HelloCommand("queue").queue();

        try {
            System.out.println(s.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        rx.Observable<String> o = new HelloCommand("observer").observe();

        o.subscribe((emitted) -> System.out.println(emitted));
        //for(int i = 0; i < 100000; i++);
    }

    static void callObservable() {
        rx.Observable<String> o = new HelloObservableCommand("suresh").observe();

        o.subscribe((s) -> System.out.println(s));
    }
}
