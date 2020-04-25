package com.example.dietapp;

public class AddPCF {
    String id;
    String ptnQuantity;

    public AddPCF()
    {}
    public AddPCF(String id, String ptnQuantity) {
        this.id = id;
        this.ptnQuantity = ptnQuantity;
    }
    public String getId() {
        return id;
    }
    public String getPtnQuantity() {
        return ptnQuantity;
    }
}
