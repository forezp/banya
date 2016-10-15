package com.forezp.banya.bean.filmusbox;

/**
 * Created by forezp on 16/9/25.
 */
public class Subjects {

    private int box;

    private boolean news;

    private int rank;

    private Subject subject;


    public int getBox() {
        return box;
    }

    public void setBox(int box) {
        this.box = box;
    }

    public boolean isNews() {
        return news;
    }

    public void setNews(boolean news) {
        this.news = news;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}
