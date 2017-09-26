package com.kushmyruk.service.impl;

import com.kushmyruk.domain.Tweet;
import com.kushmyruk.domain.User;
import com.kushmyruk.repository.TweetRepository;
import com.kushmyruk.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("tweetService")
public class SimpleTweetService implements TweetService {
    private TweetRepository tweetRepository;

    @Autowired
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
    public Tweet newTweet(String txt,User user) {
        return new Tweet(txt, user);
    }

    @Override
    public Tweet newTweet() {
        return new Tweet();
    }

    @Override
    public Integer countLike(Tweet tweet) {
        return tweet.getLike();
    }

    @Override
    public Integer countRetweet(Tweet tweet) {
        return tweet.getReTweet();
    }

    @Override
    public Integer textLength(Tweet tweet) {
        return tweet.getTxt().length();
    }

    @Override
    public User showAuthor(Tweet tweet) {
        return tweet.getUser();
    }


}
