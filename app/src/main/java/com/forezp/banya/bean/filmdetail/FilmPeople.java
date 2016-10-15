package com.forezp.banya.bean.filmdetail;

import com.forezp.banya.bean.top250.Avatars;

/**
 * Created by forezp on 16/9/25.
 */
public class FilmPeople {
    private String alt;

    private Avatars avatars;

    private String name;

    private String id;
    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public Avatars getAvatars() {
        return avatars;
    }

    public void setAvatars(Avatars avatars) {
        this.avatars = avatars;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
