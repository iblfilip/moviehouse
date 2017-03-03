package com.ibl.moviehouse.dataobjects;

import com.ibl.moviehouse.enums.UsersColumnEnum;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by Filip on 25.10.2016.
 */
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


    public void setItem(String columnName, Object item){
        if(columnName.equals(UsersColumnEnum.user_id.name()))
            setUserId((Integer)item);
        else if(columnName.equals(UsersColumnEnum.user_email.name()))
            setEmail(item.toString());
        else if(columnName.equals(UsersColumnEnum.user_name.name()))
            setName(item.toString());
        else if(columnName.equals(UsersColumnEnum.current_provider.name()))
            setCurrentProvider(item.toString());
        else if(columnName.equals(UsersColumnEnum.user_photo_url.name()))
            setUserPhotoUrl(item.toString());
        else if(columnName.equals(UsersColumnEnum.user_registration_date.name()))
            setRegistrationDate((Date) item);
        else if(columnName.equals(UsersColumnEnum.user_last_sign_in.name()))
            setLastSignIn((Timestamp) item);
        else if(columnName.equals(UsersColumnEnum.hash_code.name()))
            setHashCode((byte[]) item);
    }
}
