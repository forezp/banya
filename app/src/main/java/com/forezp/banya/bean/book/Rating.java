package com.forezp.banya.bean.book;

import java.io.Serializable;

/**
 * Created by forezp on 16/9/26.
 */
public class Rating implements Serializable{

    private int max;

    private int numRaters;

    private String average;

    private int min;

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getNumRaters() {
        return numRaters;
    }

    public void setNumRaters(int numRaters) {
        this.numRaters = numRaters;
    }

    public String getAverage() {
        return average;
    }

    public void setAverage(String average) {
        this.average = average;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }
}
