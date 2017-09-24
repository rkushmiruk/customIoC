package com.kushmyruk.runner;

import com.kushmyruk.service.TweetService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class JavaBasedConfigRunner {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext repoContext =
                new AnnotationConfigApplicationContext(RepositoryConfig.class);

        AnnotationConfigApplicationContext serviceContext =
                new AnnotationConfigApplicationContext();
        serviceContext.setParent(repoContext);
        serviceContext.register(ServiceConfig.class);
        serviceContext.getEnvironment().setActiveProfiles("dev", "test");
        serviceContext.refresh();

        TweetService tweetService = (TweetService) serviceContext.getBean("tweetService");
        System.out.println(tweetService.allTweets());
        System.out.println(tweetService.newTweet());

        System.out.println(
                tweetService.newTweet() == tweetService.newTweet()
        );


    }
}
