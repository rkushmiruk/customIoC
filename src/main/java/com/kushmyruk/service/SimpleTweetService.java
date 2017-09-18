package com.kushmyruk.service;

import com.kushmyruk.domain.Tweet;
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
    public Tweet newTweet() {
        return new Tweet();
    }

    @Override
    public String toString() {
        return "SimpleTweetService{" +
                "tweetRepository=" + tweetRepository +
                ", tweet=" + tweet +
                '}';
    }
}
