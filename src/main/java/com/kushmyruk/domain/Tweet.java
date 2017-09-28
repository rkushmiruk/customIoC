package com.kushmyruk.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Tweet {
    private Long tweetId;
    private String txt;
    private User user;
    private int like;
    private int reTweet;
    private List<String> replies = new ArrayList<>();
    private Date date;

    public Tweet(Tweet tweet) {
        this.tweetId = tweet.tweetId;
        this.txt = tweet.txt;
        this.user = tweet.user;
        this.like = tweet.like;
        this.reTweet = tweet.reTweet;
    }

    public Tweet() {
    }

    public Tweet(User user) {
        this.user = user;
    }

    public Tweet(String txt, User user) {
        this.txt = txt;
        this.user = user;
    }

    public Tweet(String txt, User user, int like, int retweet) {
        this.txt = txt;
        this.user = user;
        this.like = like;
        this.reTweet = retweet;
    }

    public Long getTweetId() {
        return tweetId;
    }

    public void setTweetId(Long tweetId) {
        this.tweetId = tweetId;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getReTweet() {
        return reTweet;
    }

    public void setReTweet(int reTweet) {
        this.reTweet = reTweet;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<String> getReplies() {
        return replies;
    }

    public void setReplies(List<String> replies) {
        this.replies = replies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tweet tweet = (Tweet) o;

        if (like != tweet.like) return false;
        if (reTweet != tweet.reTweet) return false;
        if (tweetId != null ? !tweetId.equals(tweet.tweetId) : tweet.tweetId != null) return false;
        if (txt != null ? !txt.equals(tweet.txt) : tweet.txt != null) return false;
        return user != null ? user.equals(tweet.user) : tweet.user == null;

    }

    @Override
    public int hashCode() {
        int result = tweetId != null ? tweetId.hashCode() : 0;
        result = 31 * result + (txt != null ? txt.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + like;
        result = 31 * result + reTweet;
        return result;
    }

    @Override
    public String toString() {
        return "Tweet{" +
                "tweetId=" + tweetId +
                ", txt='" + txt + '\'' +
                ", user=" + user +
                ", like=" + like +
                ", reTweet=" + reTweet +
                '}';
    }
}
