package com.hny.ar2;


public class userHelper {
    String userName,phoneNo;

    public userHelper(){

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public userHelper(String userName, String phoneNo) {
        this.userName = userName;
        this.phoneNo = phoneNo;
    }

}
