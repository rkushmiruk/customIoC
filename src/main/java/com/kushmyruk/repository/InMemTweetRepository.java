package com.kushmyruk.repository;

import com.kushmyruk.domain.Tweet;
import com.kushmyruk.ioc.BenchMark;

import java.util.Arrays;
import java.util.List;

public class InMemTweetRepository implements TweetRepository {
    private List<Tweet> tweets;

    public void init(){
        tweets = Arrays.asList(
                new Tweet(1L, "First Mesegge", null),
                new Tweet(2L, "Second Mesg", null)
        );
    }

    @Override
    @BenchMark
    public Iterable<Tweet> allTweets() {
        return tweets;
    }

    @Override
    public String toString() {
        return "InMemTweetRepository{" +
                "tweets=" + tweets +
                '}';
    }
}
