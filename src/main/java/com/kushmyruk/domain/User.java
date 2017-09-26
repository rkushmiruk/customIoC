package com.kushmyruk.domain;

import java.util.List;

public class User {
    private Long id;
    private List<Tweet> reTweets;
    private List<User> subscriptions;
    private String name;

    public User(String name) {
        this.name = name;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Tweet> getReTweets() {
        return reTweets;
    }

    public void setReTweets(List<Tweet> reTweets) {
        this.reTweets = reTweets;
    }

    public List<User> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(List<User> subscriptions) {
        this.subscriptions = subscriptions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        if (reTweets != null ? !reTweets.equals(user.reTweets) : user.reTweets != null) return false;
        if (subscriptions != null ? !subscriptions.equals(user.subscriptions) : user.subscriptions != null)
            return false;
        return name != null ? name.equals(user.name) : user.name == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (reTweets != null ? reTweets.hashCode() : 0);
        result = 31 * result + (subscriptions != null ? subscriptions.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", reTweets=" + reTweets +
                ", subscriptions=" + subscriptions +
                ", name='" + name + '\'' +
                '}';
    }
}
