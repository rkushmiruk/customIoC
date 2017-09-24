package com.kushmyruk.service;

import com.kushmyruk.domain.Tweet;
import com.kushmyruk.repository.TweetRepository;

public interface TweetService {

    Iterable<Tweet> allTweets();

    TweetRepository getRepository();

    Tweet newTweet();

    Integer countLike();

    Integer countRetweet();

    Integer textLength();

    Tweet reTweet(String txt, Tweet tweet);


}
