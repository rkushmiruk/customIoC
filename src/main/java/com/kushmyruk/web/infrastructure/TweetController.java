package com.kushmyruk.web.infrastructure;


import com.kushmyruk.domain.Tweet;
import com.kushmyruk.service.TweetService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class TweetController implements MyController {

    private TweetService tweetService;

    public TweetController(TweetService tweetService) {
        this.tweetService = tweetService;
    }

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try(PrintWriter writer = response.getWriter()) {
          for(Tweet tweet : tweetService.allTweets()) {
              writer.print(tweet + "<br/>");
          }
        }
    }
}
