package com.kushmyruk.web.infrastructure;

import com.kushmyruk.runner.RepositoryConfig;
import com.kushmyruk.runner.ServiceConfig;
import com.kushmyruk.service.TweetService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({RepositoryConfig.class, ServiceConfig.class})
public class WebInfConfig {

    @Bean
    public HandlerMapping handlerMapping() {
        return new BeanNameURLHandlerMapping();
    }

    @Bean
    public MyController hello() {
        return new HelloContoller();
    }

    @Bean
    public MyController tweets(TweetService tweetService) {
        return new TweetController(tweetService);
    }


}
