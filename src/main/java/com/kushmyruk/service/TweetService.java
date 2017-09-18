package com.kushmyruk.service;

import com.kushmyruk.domain.Tweet;
import com.kushmyruk.repository.TweetRepository;

public interface TweetService {

    Iterable<Tweet> allTweets();

    TweetRepository getRepository();

    Tweet getTweet();

    Tweet newTweet();
}
