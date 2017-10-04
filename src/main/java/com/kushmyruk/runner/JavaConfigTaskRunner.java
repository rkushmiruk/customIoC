package com.kushmyruk.runner;

import com.kushmyruk.domain.Tweet;
import com.kushmyruk.domain.User;
import com.kushmyruk.repository.TweetRepository;
import com.kushmyruk.service.TweetService;
import com.kushmyruk.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class JavaConfigTaskRunner {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext repoContext =
                new AnnotationConfigApplicationContext(RepositoryConfig.class);

        AnnotationConfigApplicationContext serviceContext =
                new AnnotationConfigApplicationContext();
        serviceContext.setParent(repoContext);
        serviceContext.register(ServiceConfig.class);
        serviceContext.refresh();

        TweetService tweetService = (TweetService) serviceContext.getBean("tweetService");
        TweetRepository tweetRepository = (TweetRepository) serviceContext.getBean("tweetRepository");
        UserService userService = (UserService) serviceContext.getBean("userService");

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
        System.out.println(tweetService.countLike(tweet));

        Tweet retweet = userService.retweet("Retweet text", tweet);

        System.out.println(retweet.getTxt());

    }
}
