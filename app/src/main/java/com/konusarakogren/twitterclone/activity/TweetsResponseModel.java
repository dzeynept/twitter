package com.konusarakogren.twitterclone.activity;

public class TweetsResponseModel {

    private String tweet;
    private String userName;
    private int likes;
    private String objectId;

    public TweetsResponseModel() {
    }

    public String getTweet() {
        return tweet;
    }

    public void setTweet(String tweet) {
        this.tweet = tweet;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    @Override
    public String toString() {
        return "TweetsResponseModel{" +
                "tweet='" + tweet + '\'' +
                ", userName='" + userName + '\'' +
                ", likes=" + likes +
                ", objectId='" + objectId + '\'' +
                '}';
    }
}
