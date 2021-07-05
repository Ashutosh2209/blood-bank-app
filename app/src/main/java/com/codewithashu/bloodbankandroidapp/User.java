package com.codewithashu.bloodbankandroidapp;

public class User {

    String mSignupname/*,mSignupbloodgroup*/;
    long /*mSignupage,*/ mSignupphone;

    public User(){}

    public User(String mSignupname, String mSignupbloodgroup, long mSignupage, long mSignupphone) {
        this.mSignupname = mSignupname;
//        this.mSignupbloodgroup = mSignupbloodgroup;
//        this.mSignupage = mSignupage;
        this.mSignupphone = mSignupphone;
    }

    public String getmSignupname() {
        return mSignupname;
    }

    public void setmSignupname(String mSignupname) {
        this.mSignupname = mSignupname;
    }

//    public String getmSignupbloodgroup() {
//        return mSignupbloodgroup;
//    }
//
//    public void setmSignupbloodgroup(String mSignupbloodgroup) {
//        this.mSignupbloodgroup = mSignupbloodgroup;
//    }

//    public long getmSignupage() {
//        return mSignupage;
//    }
//
//    public void setmSignupage(long mSignupage) {
//        this.mSignupage = mSignupage;
//    }

    public long getmSignupphone() {
        return mSignupphone;
    }

    public void setmSignupphone(long mSignupphone) {
        this.mSignupphone = mSignupphone;
    }
}
