package com.kushmyruk.domain;

public class Tweet {
    private Long tweetId;
    private String txt;
    private User user;
    private int like;
    private int retweet;

    public Tweet(Tweet tweet) {
        this.tweetId = tweet.tweetId;
        this.txt = tweet.txt;
        this.user = tweet.user;
        this.like = tweet.like;
        this.retweet = tweet.retweet;
    }

    public Tweet() {
    }

    public Tweet(String txt, User user) {
        this.txt = txt;
        this.user = user;
    }

    public Tweet(String txt, User user, int like, int retweet) {
        this.txt = txt;
        this.user = user;
        this.like = like;
        this.retweet = retweet;
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

    public int getRetweet() {
        return retweet;
    }

    public void setRetweet(int retweet) {
        this.retweet = retweet;
    }

    @Override
    public String toString() {
        return "Tweet{" +
                "tweetId=" + tweetId +
                ", txt='" + txt + '\'' +
                ", user=" + user +
                ", like=" + like +
                ", retweet=" + retweet +
                '}';
    }
}
