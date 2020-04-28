package com.example.dietapp;

public class SetMeal {
   String id;
    String ProtienSM;
    String CarbsSM;
    String FatsSM;
    String UserId;
    int d1;
    int d2;
    int d3;

    public SetMeal() {

    }

    public SetMeal(String id, String protienSM, String carbsSM, String fatsSM) {
        this.id = id;
        this.ProtienSM = protienSM;
        this.CarbsSM = carbsSM;
        this.FatsSM = fatsSM;
    }

    public SetMeal(String protienSM, String carbsSM, String fatsSM) {
        ProtienSM = protienSM;
        CarbsSM = carbsSM;
        FatsSM = fatsSM;
    }


    public String getId() {
        return id;
    }

    public String getProtienSM() {
        return ProtienSM;
    }

    public String getCarbsSM() {
        return CarbsSM;
    }

    public String getFatsSM() {
        return FatsSM;
    }

    public String getUserId() {
        return UserId;
    }

    public SetMeal(String id, int d1, int d2, int d3) {
        this.id = id;
        this.d1 = d1;
        this.d2 = d2;
        this.d3 = d3;
    }

    public int getD1() {
        return d1;
    }

    public int getD2() {
        return d2;
    }

    public int getD3() {
        return d3;
    }
}
