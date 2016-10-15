package com.forezp.banya.bean.music;

import java.util.List;

/**
 * Created by forezp on 16/9/30.
 */
public class MusicRoot {
    private int count;

    private int start;

    private int total;

    private List<Musics> musics ;

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

    public List<Musics> getMusics() {
        return musics;
    }

    public void setMusics(List<Musics> musics) {
        this.musics = musics;
    }
}
