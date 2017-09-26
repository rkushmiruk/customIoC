package com.kushmyruk.service.impl;

import com.kushmyruk.domain.Tweet;
import com.kushmyruk.domain.User;
import com.kushmyruk.service.TweetService;
import com.kushmyruk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("userService")
public class SimpleUserService implements UserService {

    @Autowired
    private TweetService tweetService;

    @Override
    public void like(Tweet tweet) {
        tweet.setLike(tweet.getLike() + 1);
    }

    @Override
    public Tweet retweet(String txt, Tweet oldTweet) {
        Tweet tweet = new Tweet(oldTweet);
        tweet.setTxt(txt + " " + oldTweet.getTxt());
        oldTweet.setReTweet(oldTweet.getReTweet() + 1);
        return tweet;
    }

    @Override
    public void subscribe(User subscriber, User author) {
        author.getSubscriptions().add(subscriber);
    }

    @Override
    public void reply(String txt, Tweet tweet) {
        tweet.getReplies().add(txt);
    }

    @Override
    public Iterable<Tweet> wall(User user) {
        List<Tweet> tweets;

        tweets = ((List<Tweet>) tweetService.allTweets()).stream()
                .filter(e -> e.getUser().equals(user))
                .collect(Collectors.toList());

        return tweets;
    }
}
