package com.ibl.moviehouse.dataobjects;

import java.sql.Date;
import java.sql.Timestamp;

public class User {
    Integer userId;
    String email;
    String name;
    byte[] hashCode;
    String currentProvider;
    String userPhotoUrl;
    Date registrationDate;
    Timestamp lastSignIn;

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHashCode(byte[] hashCode) {
        this.hashCode = hashCode;
    }

    public void setCurrentProvider(String currentProvider) {
        this.currentProvider = currentProvider;
    }

    public void setUserPhotoUrl(String userPhotoUrl) {
        this.userPhotoUrl = userPhotoUrl;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public void setLastSignIn(Timestamp lastSignIn) {
        this.lastSignIn = lastSignIn;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public byte[] getHashCode() {
        return hashCode;
    }

    public String getName() {
        return name;
    }

    public String getUserPhotoUrl() {
        return userPhotoUrl;
    }

    public String getCurrentProvider() {
        return currentProvider;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public Timestamp getLastSignIn() {
        return lastSignIn;
    }

    public String toString() {
        return "user: userId= " + userId + ", email=" + email + ", name=" + name + ", hashCode=" + hashCode + ", currentProvider=" + currentProvider + ", userPhotoUrl=" + userPhotoUrl + ", registrationDate=" + registrationDate + ", lastSignIn=" + lastSignIn;
    }
}

