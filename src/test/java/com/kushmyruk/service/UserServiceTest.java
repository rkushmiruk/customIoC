package com.kushmyruk.service;

import com.kushmyruk.domain.Tweet;
import com.kushmyruk.domain.User;
import com.kushmyruk.repository.TweetRepository;
import com.kushmyruk.service.impl.SimpleTweetService;
import com.kushmyruk.service.impl.SimpleUserService;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class UserServiceTest {
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
    public void createNewUserTest() {
        SimpleUserService userService = new SimpleUserService();
        userService.setTweetRepository(tweetRepository);
        String username = "aaa";
        User actual = userService.createNewUser(username);
        assertEquals(username, actual.getName());
    }

    @Test
    public void testUserSubscription() {
        User subscriber = new User("subscriber");
        User author = new User("author");
        userService.subscribe(subscriber, author);

        assertEquals(1, author.getSubscriptions().size());
    }

    @Test
    public void testUserReplyMessageLength() {
        Tweet tweet = new Tweet();
        userService.reply("txt", tweet);

        assertEquals(3, tweet.getReplies().get(0).length());
    }

    @Test
    public void testCountTweetReplies() {
        Tweet tweet = new Tweet();
        userService.reply("txt", tweet);
        userService.reply("txt", tweet);
        userService.reply("txt", tweet);
        userService.reply("txt", tweet);

        assertEquals(4, tweet.getReplies().size());
    }

    @Test
    public void checkUserWallTwitter() {
        User user = new User();
        Tweet tweet1 = tweetService.newTweet("txt1",user);
        Tweet tweet2 = tweetService.newTweet("txt2",user);
        Tweet tweet3 = tweetService.newTweet("txt3",user);
        Tweet tweet4 = tweetService.newTweet("txt4",user);

        assertEquals(4,userService.wall(user).size());
    }


}
