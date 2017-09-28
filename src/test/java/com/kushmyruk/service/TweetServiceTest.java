package com.kushmyruk.service;

import com.kushmyruk.domain.Tweet;
import com.kushmyruk.domain.User;
import com.kushmyruk.repository.TweetRepository;
import com.kushmyruk.service.impl.SimpleTweetService;
import com.kushmyruk.service.impl.SimpleUserService;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TweetServiceTest {
    TweetRepository tweetRepository;
    TweetService tweetService;
    UserService userService;

    @Before
    public void init() {
        tweetRepository = mock(TweetRepository.class);
        tweetService = new SimpleTweetService(tweetRepository);
        userService = new SimpleUserService();
    }

    @Test
    public void getAllTweetsTest() {
        User user = new User("aaa");
        List<Tweet> tweets = new ArrayList<>();
        tweets.add(tweetService.newTweet("", user));
        tweets.add(tweetService.newTweet("", user));
        tweets.add(tweetService.newTweet("", user));

        User otherUser = new User("bbb");
        tweets.add(tweetService.newTweet("", otherUser));

        when(tweetRepository.allTweets()).thenReturn(tweets);
        Iterable<Tweet> result = tweetService.allTweets();
        int itemCounter = 0;
        for (Tweet t : result) {
            itemCounter++;
        }
        assertEquals(4, itemCounter);
    }

    @Test
    public void testNumberOfLikeInTweet() {
        Tweet tweet = tweetService.newTweet();
        userService.like(tweet);
        userService.like(tweet);
        userService.like(tweet);
        userService.like(tweet);
        assertEquals(4, tweet.getLike());
    }

    @Test
    public void testNumberOfRetweetsInTweet() {
        Tweet tweet = tweetService.newTweet();
        userService.retweet("", tweet);
        userService.retweet("", tweet);
        userService.retweet("", tweet);
        userService.retweet("", tweet);
        userService.retweet("", tweet);

        assertEquals(5, tweet.getReTweet());
    }

    @Test
    public void TxtLengthInTweetCount() {
        Tweet tweet = tweetService.newTweet("Symbol", new User());
        int actual = tweetService.textLength(tweet);
        assertEquals(6, actual);
    }

    @Test
    public void ZeroTxtLengthInTweetCount() {
        Tweet tweet = tweetService.newTweet("", new User());
        int actual = tweetService.textLength(tweet);
        assertEquals(0, actual);
    }

}
