package com.kushmyruk.service;

import com.kushmyruk.domain.Tweet;
import com.kushmyruk.domain.User;

import java.util.List;

public interface UserService {
    User createNewUser(String name);

    void like(Tweet tweet);

    Tweet retweet(String txt, Tweet oldTweet);

    void subscribe(User subscriber, User author);

    void reply(String txt, Tweet oldTweet);

    List<Tweet> wall(User user);


}
