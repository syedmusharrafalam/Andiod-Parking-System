package com.parkingsystem.booking;

/**
 * Created by User on 4/15/2018.
 */

public class Booking {
    String name;
    String contact;
    String email;
    String uid;
    String bid;
    String startMils;
    String endMils;
    int slots;


    public Booking() {
    }

    public Booking(String name, String contact, String email, String uid, String bid, String startMils, String endMils, int slots) {
        this.name = name;
        this.contact = contact;
        this.email = email;
        this.uid = uid;
        this.bid = bid;
        this.startMils = startMils;
        this.endMils = endMils;
        this.slots = slots;
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

    public int getSlots() {
        return slots;
    }

    public String getUid() {
        return uid;
    }

    public String getBid() {
        return bid;
    }

    public String getStartMils() {
        return startMils;
    }

    public String getEndMils() {
        return endMils;
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

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public void setStartMils(String startMils) {
        this.startMils = startMils;
    }

    public void setEndMils(String endMils) {
        this.endMils = endMils;
    }

    public void setSlots(int slots) {
        this.slots = slots;
    }
}