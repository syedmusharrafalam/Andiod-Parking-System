package com.parkingsystem.userpanel.activities;

/**
 * Created by User on 4/12/2018.
 */

public class UserInformation {
    String uid;
    String name;
    String contact;
    String email;
    String password;


    public UserInformation() {
    }

    public UserInformation(String uid, String name, String contact, String email, String password) {
        this.uid = uid;
        this.name = name;
        this.contact = contact;
        this.email = email;
        this.password = password;
    }

    public String getUid() {
        return uid;
    }

    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
