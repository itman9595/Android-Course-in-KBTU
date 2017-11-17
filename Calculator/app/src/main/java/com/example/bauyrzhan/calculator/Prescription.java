package com.example.bauyrzhan.calculator;

/**
 * Created by Bauyrzhan on 11/15/17.
 */

public class Prescription {

    private final String name;
    private final String dose;
    private final String quantity;
    private final String frequency;
    private final String duration;

    public Prescription(String name, String dose, String quantity, String frequency, String duration) {
        this.name = name;
        this.dose = dose;
        this.quantity = quantity;
        this.frequency = frequency;
        this.duration = duration;
    }

    public String getName() { return name; }

    public String getDose() { return dose; }

    public String getQuantity() { return quantity; }

    public String getFrequency() { return frequency; }

    public String getDuration() { return duration; }

}
