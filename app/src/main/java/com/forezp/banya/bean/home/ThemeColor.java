package com.forezp.banya.bean.home;

/**
 * Created by gejiahui on 2016/3/28.
 */
public class ThemeColor {

    int color;
    boolean isChosen = false;

    public ThemeColor(int color) {
        this.color = color;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public boolean isChosen() {
        return isChosen;
    }

    public void setChosen(boolean chosen) {
        isChosen = chosen;
    }

}
