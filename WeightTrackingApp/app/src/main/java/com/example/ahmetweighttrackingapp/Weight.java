package com.example.ahmetweighttrackingapp;

import android.app.Activity;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

public class Weight {
    private int id;
    private String date;
    private String weight;
    private String deleted;

    public static ArrayList<Weight> weightsList = new ArrayList<>();
    public static String weightExtra = "editWeight";

    public Weight(int id, String date, String weight) {
        this.id = id;
        this.date = date;
        this.weight = weight;
        deleted = null;
    }

    public Weight(int id, String date, String weight, String deleted) {
        this.id = id;
        this.date = date;
        this.weight = weight;
        this.deleted = deleted;
    }

    // Finds weight by ID
    public static Weight getWeightForID(int passedWeightID) {
        for (Weight weight : weightsList) {
            if (weight.getId() == passedWeightID)
                return weight;
        }
        return null;
    }

    // Returns a list of all weight entries that have a null deleted field
    public static ArrayList<Weight> unDeletedNodes() {
        ArrayList<Weight> nonDelList = new ArrayList<>();
        for (Weight weight : weightsList) {
            if (weight.getDeleted() == null)
                nonDelList.add(weight);
        }
        return nonDelList;
    }

    // Accessors and Mutators for the weight object
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

}
