package com.codewithashu.bloodbankandroidapp;

public class modelRequest {
    public String blood_Units, blood_Group;

    public modelRequest(){

    }

    public modelRequest(String blood_Units, String blood_Group) {
        this.blood_Units = blood_Units;
        this.blood_Group = blood_Group;
    }

    public String getBlood_Units() {
        return blood_Units;
    }

    public String getBlood_Group() {
        return blood_Group;
    }
}
