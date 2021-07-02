package com.codewithashu.bloodbankandroidapp;

public class modelDonate {
    public String blood_ID, blood_Units, blood_Group;

    public modelDonate(){
    }

    public modelDonate(String blood_ID, String blood_Units, String blood_Group) {
        this.blood_ID = blood_ID;
        this.blood_Units = blood_Units;
        this.blood_Group = blood_Group;
    }

    public String getBlood_ID() {
        return blood_ID;
    }

    public String getBlood_Units() {
        return blood_Units;
    }

    public String getBlood_Group() {
        return blood_Group;
    }
}
