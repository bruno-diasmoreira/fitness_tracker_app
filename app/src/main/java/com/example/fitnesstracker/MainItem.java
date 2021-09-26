package com.example.fitnesstracker;

public class MainItem {

    private int id;
    private int drawableId;
    private int TextStringId;


    public MainItem(int id, int drawableId, int textStringId) {
        this.id = id;
        this.drawableId = drawableId;
        TextStringId = textStringId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDrawableId() {
        return drawableId;
    }

    public void setDrawableId(int drawableId) {
        this.drawableId = drawableId;
    }

    public int getTextStringId() {
        return TextStringId;
    }

    public void setTextStringId(int textStringId) {
        TextStringId = textStringId;
    }
}
