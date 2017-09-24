package com.kushmyruk.service.impl;

import com.kushmyruk.domain.Tweet;
import com.kushmyruk.repository.TweetRepository;
import com.kushmyruk.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("tweetService")
public class SimpleTweetService implements TweetService {

    @Autowired
    private TweetRepository tweetRepository;
    private Tweet tweet;

    public SimpleTweetService(TweetRepository tweetRepository) {
        this.tweetRepository = tweetRepository;
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
    public Integer countLike() {
        return tweet.getLike();
    }

    @Override
    public Integer countRetweet() {
        return tweet.getRetweet();
    }

    @Override
    public Integer textLength() {
        return tweet.getTxt().length();
    }

    public Tweet reTweet(String txt, Tweet tweet) {
        Tweet retweet = new Tweet(tweet);
        retweet.setTxt(tweet.getTxt() + " " + txt);
        tweetRepository.allTweets().add(retweet);
        return retweet;
    }

    @Override
    public String toString() {
        return "SimpleTweetService{" +
                "tweetRepository=" + tweetRepository +
                ", tweet=" + tweet +
                '}';
    }
}
