package com.kushmyruk.service;

import com.kushmyruk.domain.Tweet;
import com.kushmyruk.domain.User;
import com.kushmyruk.repository.TweetRepository;
import com.kushmyruk.runner.RepositoryConfig;
import com.kushmyruk.runner.ServiceConfig;
import com.kushmyruk.service.impl.SimpleUserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@ContextHierarchy({
        @ContextConfiguration(classes = RepositoryConfig.class),
        @ContextConfiguration(classes = ServiceConfig.class)
})
public class UserServiceTest {
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
        Tweet tweet1 = tweetService.newTweet("txt1", user);
        Tweet tweet2 = tweetService.newTweet("txt2", user);
        Tweet tweet3 = tweetService.newTweet("txt3", user);
        Tweet tweet4 = tweetService.newTweet("txt4", user);

        assertEquals(4, userService.wall(user).size());
    }


}
