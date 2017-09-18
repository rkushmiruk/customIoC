package com.kushmyruk;

import com.kushmyruk.domain.Tweet;
import com.kushmyruk.ioc.ApplicationContext;
import com.kushmyruk.ioc.Config;
import com.kushmyruk.ioc.Context;
import com.kushmyruk.ioc.JavaMapConfig;
import com.kushmyruk.repository.InMemTweetRepository;
import com.kushmyruk.repository.TweetRepository;
import com.kushmyruk.service.SimpleTweetService;
import com.kushmyruk.service.TweetService;

import java.util.HashMap;
import java.util.Map;

public class IoCRunner {
    public static void main(String[] args) {
        Map<String, Map<String, Object>> beanDescriptions =
                new HashMap<String, Map<String, Object>>() {{
                    put("tweetRepository",
                            new HashMap<String, Object>() {{
                                put("type", InMemTweetRepository.class);
                                put("isPrototype", true);
                            }}
                    );
                    put("tweetService",
                            new HashMap<String, Object>() {{
                                put("type", SimpleTweetService.class);
                                put("isPrototype", false);
                            }}
                    );
                    put("tweet",
                            new HashMap<String, Object>() {{
                                put("type", Tweet.class);
                                put("isPrototype", true);
                            }}
                    );
                }};

        Config config = new JavaMapConfig(beanDescriptions);
        Context context = new ApplicationContext(config);

        TweetService tweetService = (TweetService) context.getBean("tweetService");
        TweetRepository tweetRepository = (TweetRepository) context.getBean("tweetRepository");

        System.out.println(tweetService.newTweet() == tweetService.newTweet());

    }
}
