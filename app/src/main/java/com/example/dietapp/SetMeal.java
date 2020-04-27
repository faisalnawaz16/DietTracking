package com.example.dietapp;

public class SetMeal {
    String id;
    String ProtienSM;
    String CarbsSM;
    String FatsSM;

    public SetMeal() {

    }

    public SetMeal(String id, String protienSM, String carbsSM, String fatsSM) {
        this.id = id;
        this.ProtienSM = protienSM;
        this.CarbsSM = carbsSM;
        this.FatsSM = fatsSM;
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
}
