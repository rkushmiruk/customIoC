package com.kushmyruk.repository;

import com.kushmyruk.domain.Tweet;
import com.kushmyruk.domain.User;

import java.util.List;

public interface TweetRepository {

    List<Tweet> allTweets();
    List<User> allUsers();
}
