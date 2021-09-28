package com.example.fitnesstracker.model;

public class Register {

    private String type,created_date;
    private double result;
    private int id;


    public Register() {
    }

    public Register(int id, String type, String created_date, double result) {
        this.id = id;
        this.type = type;
        this.created_date = created_date;
        this.result = result;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
