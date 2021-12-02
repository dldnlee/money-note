package com.example.moneynote.models;

public class UserDataModel {
    private String date;
    private String category;
    private int amount;
    private String description;
    private String type;

    public UserDataModel(String type, String date, String category, int amount, String description) {
        this.type = type;
        this.date = date;
        this.category = category;
        this.amount = amount;
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
