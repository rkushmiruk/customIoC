package com.kushmyruk;

import com.kushmyruk.domain.Tweet;
import com.kushmyruk.repository.TweetRepository;
import com.kushmyruk.service.TweetService;
import org.springframework.context.support.GenericXmlApplicationContext;

public class SpringXmlRunner {
    public static void main(String[] args) {
        GenericXmlApplicationContext repoContext = new GenericXmlApplicationContext("repository-configuration-xml.xml");

        GenericXmlApplicationContext serviceContext = new GenericXmlApplicationContext("service-configuration-xml.xml");

        TweetService tweetService = (TweetService) serviceContext.getBean("tweetService");
        TweetRepository tweetRepository = (TweetRepository) serviceContext.getBean("tweetRepository");

        System.out.println(tweetRepository.allTweets());
        System.out.println(tweetService.allTweets());

        Tweet tweet = (Tweet) serviceContext.getBean("tweet");
        System.out.println(tweet);

        serviceContext.close();
        repoContext.close();


    }
}
