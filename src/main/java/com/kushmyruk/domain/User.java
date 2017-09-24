package com.kushmyruk.domain;

public class User {
    private Tweet tweet;
    private String name;

    public User(Tweet tweet) {
        this.tweet = tweet;
    }

    public User(String name) {
        this.name = name;
    }

    public User() {
    }

    public void setTweet(Tweet tweet) {
        this.tweet = tweet;
    }

    @Override
    public String toString() {
        return "User{" +
                "tweet=" + tweet +
                ", name='" + name + '\'' +
                '}';
    }
}
