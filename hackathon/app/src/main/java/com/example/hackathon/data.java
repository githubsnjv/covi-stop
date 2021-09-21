package com.example.hackathon;

public class data {
    String oxygen, temp, place;

    public data(String oxygen, String temp, String place) {
        this.oxygen = oxygen;
        this.temp = temp;
        this.place = place;
    }

    public data() {
    }

    public String getOxygen() {
        return oxygen;
    }

    public String getTemp() {
        return temp;
    }

    public String getPlace() {
        return place;
    }
}