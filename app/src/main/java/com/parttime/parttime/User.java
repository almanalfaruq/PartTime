package com.parttime.parttime;

import java.io.File;

public class User {

    private String userId;
    private String name;
    private String ttl;
    private String address;
    private File cv;

    public User() {
    }

    public User(String userId, String name, String ttl, String address) {
        this.userId = userId;
        this.name = name;
        this.ttl = ttl;
        this.address = address;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTtl() {
        return ttl;
    }

    public void setTtl(String ttl) {
        this.ttl = ttl;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public File getCv() {
        return cv;
    }

    public void setCv(File cv) {
        this.cv = cv;
    }
}
