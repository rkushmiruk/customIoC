package com.kushmyruk.service.impl;

import com.kushmyruk.domain.Tweet;
import com.kushmyruk.service.TweetService;
import com.kushmyruk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class SimpleUserService implements UserService {

    @Autowired
    private TweetService tweetService;

    @Override
    public void giveLike(Tweet tweet) {
        tweet.setLike(tweet.getLike() + 1);
    }

    @Override
    public Tweet doRetweet(String txt, Tweet tweet) {
        return tweetService.reTweet(txt, tweet);
    }
}
