package com.codewithashu.bloodbankandroidapp;

public class modelAdd {
    public String blood_ID, blood_Units, blood_Group;

    public modelAdd(){
    }

    public modelAdd(String blood_ID, String blood_Units, String blood_Group) {
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
