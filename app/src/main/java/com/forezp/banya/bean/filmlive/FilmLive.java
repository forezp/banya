package com.forezp.banya.bean.filmlive;

import com.forezp.banya.bean.top250.Subjects;

import java.util.List;

/**
 * Created by forezp on 16/9/22.
 */
public class FilmLive {

    private int count;

    private int start;

    private int total;

    private List<Subjects> subjects ;

    private String title;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Subjects> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subjects> subjects) {
        this.subjects = subjects;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
