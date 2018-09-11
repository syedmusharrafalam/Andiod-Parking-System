package com.parkingsystem.feedbackUser;

/**
 * Created by User on 4/24/2018.
 */

public class FeedBackUid {
    String uid;
    String name;
    String fid;

    public FeedBackUid() {
    }

    public FeedBackUid(String uid, String name,String fid) {
        this.uid = uid;
        this.name = name;
        this.fid=fid;
    }

    public String getUid() {
        return uid;
    }

    public String getName() {
        return name;
    }

    public String getFid() {
        return fid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }
}
