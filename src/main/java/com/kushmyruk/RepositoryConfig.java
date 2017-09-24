package com.kushmyruk;

import com.kushmyruk.repository.InMemTweetRepository;
import com.kushmyruk.repository.TweetRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfig {

    @Bean(initMethod = "init")
    public TweetRepository tweetRepository() {
        return new InMemTweetRepository();
    }

}
