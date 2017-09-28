package com.kushmyruk.repository;

import com.kushmyruk.domain.Tweet;
import com.kushmyruk.domain.User;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Repository("tweetRepository")
public class InMemTweetRepository implements TweetRepository {

    private List<Tweet> tweets = new ArrayList<>();
    private List<User> users = new ArrayList<>();

    @PostConstruct
    public void init() {
        tweets.add(new Tweet("First Mesg", null));
        tweets.add(new Tweet("Second Mesg", null));
    }

    @Override
    public List<Tweet> allTweets() {
        return tweets;
    }

    @Override
    public List<User> allUsers() {
        return users;
    }
}
