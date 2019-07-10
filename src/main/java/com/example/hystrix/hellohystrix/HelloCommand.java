package com.example.hystrix.hellohystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

public class HelloCommand extends HystrixCommand<String> {
    String _name;

    public HelloCommand(String name){
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
        this._name = name;
    }

    @Override
    protected String run() {
        return "Hello " + this._name + "!";
    }
}
