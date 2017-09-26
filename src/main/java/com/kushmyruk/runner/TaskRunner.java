package com.kushmyruk.runner;

import com.kushmyruk.domain.Tweet;
import com.kushmyruk.domain.User;
import com.kushmyruk.repository.TweetRepository;
import com.kushmyruk.service.TweetService;
import com.kushmyruk.service.UserService;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TaskRunner {
    public static void main(String[] args) {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        context.load("service-configuration-xml.xml");
        context.refresh();

        TweetService tweetService = (TweetService) context.getBean("tweetService");
        TweetRepository tweetRepository = (TweetRepository) context.getBean("tweetRepository");
        UserService userService = (UserService) context.getBean("userService");

        System.out.println(tweetService);
        System.out.println(tweetRepository);
        System.out.println(userService);
        User user = new User("Roma");

        Tweet tweet = new Tweet("Tweet", user);

        userService.like(tweet);
        userService.like(tweet);
        userService.like(tweet);
        userService.like(tweet);

        System.out.println(tweet.getTxt());

        System.out.println(tweet.getLike());

        Tweet retweet = userService.retweet("Retweet text", tweet);

        System.out.println(retweet.getTxt());
    }
}
