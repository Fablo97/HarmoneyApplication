package com.example.harmoneyapp;

public class ModelClass {
    private int imageview1;
    private String textview;
    private String textview2;
    private String textview3;

    ModelClass(int imageview1, String textview, String textview2, String textview3) {
    this.imageview1=imageview1;
    this.textview=textview;
    this.textview2=textview2;
    this.textview3=textview3;
    }

    public int getImageview1() {
        return imageview1;
    }

    public String getTextview() {
        return textview;
    }

    public String getTextview2() {
        return textview2;
    }

    public String getTextview3() {
        return textview3;
    }
}
