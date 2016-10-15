package com.forezp.banya.bean.filmusbox;


import java.util.List;

/**
 * Created by forezp on 16/9/25.
 */
public class FilmUsBox   {

    private String date;

    private List<Subjects> subjects ;

    private String title;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
