package com.kushmyruk.repository;

import com.kushmyruk.domain.Tweet;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@Repository("tweetRepository")
public class InMemTweetRepository implements TweetRepository {

    private List<Tweet> tweets;

    @PostConstruct
    public void init() {
        tweets = Arrays.asList(
                new Tweet("First Mesg", null),
                new Tweet("Second Mesg", null)

        );

    }

    @Override
    public Iterable<Tweet> allTweets() {
        return tweets;
    }
}
