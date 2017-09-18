package com.kushmyruk.repository;

import com.kushmyruk.domain.Tweet;

public interface TweetRepository {

    Iterable<Tweet> allTweets();
}
