package com.kushmyruk.service;

import com.kushmyruk.domain.Tweet;
import com.kushmyruk.domain.User;
import com.kushmyruk.repository.TweetRepository;

public interface TweetService {

    Iterable<Tweet> allTweets();

    TweetRepository getRepository();

    Tweet newTweet(String txt,User user);

    Tweet newTweet();

    Integer countLike(Tweet tweet);

    Integer countRetweet(Tweet tweet);

    Integer textLength(Tweet tweet);

    String showAuthor(Tweet tweet);

}
