package com.example.lab__7;

public class Character {

    private final String name;
    private final String height;
    private final String mass;

    public Character(String name, String height, String mass) {
        this.name = name;
        this.height = height;
        this.mass = mass;
    }

    public String getName() {
        return name;
    }

    public String getHeight() {
        return height;
    }

    public String getMass() {
        return mass;
    }
}
