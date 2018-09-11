package com.parkingsystem.feedbackUser;

/**
 * Created by User on 4/24/2018.
 */

public class FeedBackInf {
    String fid;
    String uid;
    String name;
    String message;

    public FeedBackInf() {
    }

    public FeedBackInf(String fid, String uid, String name, String message) {
        this.fid = fid;
        this.uid = uid;
        this.name = name;
        this.message = message;
    }

    public String getFid() {
        return fid;
    }

    public String getUid() {
        return uid;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }


    public void setFid(String fid) {
        this.fid = fid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
