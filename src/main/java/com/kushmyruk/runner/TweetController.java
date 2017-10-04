package com.kushmyruk.runner;

import com.kushmyruk.domain.Tweet;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TweetController {

    @RequestMapping(value = "/")
    @ResponseBody
    public String Hello() {
        return "Hello from tweet controller";
    }

    @GetMapping("/tweet")
    public String tweet(Model model){
        Tweet tweet = new Tweet();
        model.addAttribute(tweet);

        return "tweet";
    }
}
