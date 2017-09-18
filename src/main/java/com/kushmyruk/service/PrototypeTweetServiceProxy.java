package com.kushmyruk.service;

import com.kushmyruk.ioc.Context;

import java.lang.reflect.Proxy;

public class PrototypeTweetServiceProxy {
    private TweetService realTweetService;
    private Context context;

    public PrototypeTweetServiceProxy(TweetService realTweetService, Context context) {
        this.realTweetService = realTweetService;
        this.context = context;
    }

    public TweetService createProxy() {
        return (TweetService) Proxy.newProxyInstance(
                ClassLoader.getSystemClassLoader(),
                new Class[]{TweetService.class},
                (proxy, method, args) -> {
                    if (method.getName().equals("newTweet")) {
                        return context.getBean("tweet");
                    } else {
                        return method.invoke(realTweetService, args);
                    }
                }
        );
    }
}
