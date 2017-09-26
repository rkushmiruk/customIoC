package com.kushmyruk.service;

import com.kushmyruk.domain.Tweet;
import com.kushmyruk.domain.User;

public interface UserService {
    void like(Tweet tweet);

    Tweet retweet(String txt, Tweet oldTweet);

    void subscribe(User subscriber, User author);

    void reply(String txt, Tweet oldTweet);

    Iterable<Tweet> wall(User user);


}
