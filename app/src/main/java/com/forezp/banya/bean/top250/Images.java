package com.forezp.banya.bean.top250;

import java.io.Serializable;

/**
 * Created by forezp on 16/9/20.
 */
public class Images implements Serializable {
    private String small;

    private String large="";

    private String medium;


    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }
}
