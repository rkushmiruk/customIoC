package com.kushmyruk.service;

import com.kushmyruk.domain.Tweet;
import com.kushmyruk.ioc.Lookup;
import com.kushmyruk.repository.TweetRepository;

public class SimpleTweetService implements TweetService {
    private final TweetRepository tweetRepository;
    private Tweet tweet;

    public SimpleTweetService(TweetRepository tweetRepository, Tweet tweet) {
        this.tweetRepository = tweetRepository;
        this.tweet = tweet;
    }

    @Override
    public Iterable<Tweet> allTweets() {
        return tweetRepository.allTweets();
    }

    @Override
    public TweetRepository getRepository() {
        return tweetRepository;
    }

    @Override
    @Lookup(className = "Tweet")
    public Tweet newTweet() {
        return new Tweet();
    }

    public Tweet getTweet() {
        return tweet;
    }

    public void setTweet(Tweet tweet) {
        this.tweet = tweet;
    }

    @Override
    public String toString() {
        return "SimpleTweetService{" +
                "tweetRepository=" + tweetRepository +
                ", tweet=" + tweet +
                '}';
    }
}
