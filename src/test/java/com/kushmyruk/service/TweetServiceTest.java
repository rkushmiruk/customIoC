package com.kushmyruk.service;

import com.kushmyruk.domain.Tweet;
import com.kushmyruk.domain.User;
import com.kushmyruk.repository.TweetRepository;
import com.kushmyruk.runner.RepositoryConfig;
import com.kushmyruk.runner.ServiceConfig;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextHierarchy({
        @ContextConfiguration(classes = RepositoryConfig.class),
        @ContextConfiguration(classes = ServiceConfig.class)
})
public class TweetServiceTest {
    TweetRepository tweetRepository;
    @Autowired
    TweetService tweetService;
    @Autowired
    UserService userService;

    @Before
    public void init() {
        tweetRepository = mock(TweetRepository.class);
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
        assertEquals(6, itemCounter);
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

    @Test(expected = RuntimeException.class)
    public void MaxTweetLengthTest(){
        String message = "amdsmdasmdmasdmasmdasmdmasdmasmdamdmasmdasmasmdma" +
                "admadmasmdamdamdmamdamdammadmasmdmadmamdamdamsdamdmamdamma";

        tweetService.newTweet(message,new User());
    }

    @Test
    public void ZeroTxtLengthInTweetCount() {
        Tweet tweet = tweetService.newTweet("", new User());
        int actual = tweetService.textLength(tweet);
        assertEquals(0, actual);
    }

    @Test
    public void testTweetAuthor() {
        Tweet tweet = tweetService.newTweet("", new User("Roman"));

        assertEquals("Roman", tweetService.showAuthor(tweet));
    }

}
