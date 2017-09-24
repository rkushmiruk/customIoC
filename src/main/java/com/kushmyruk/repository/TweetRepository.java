package com.kushmyruk.repository;

import com.kushmyruk.domain.Tweet;

import java.util.List;

public interface TweetRepository {

    List<Tweet> allTweets();
}
