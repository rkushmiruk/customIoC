package com.kushmyruk.service;

import com.kushmyruk.domain.Tweet;

public interface UserService {
    void giveLike(Tweet tweet);

    Tweet doRetweet(String txt, Tweet tweet);
}
