package com.example.musicity;

import android.widget.Button;

public class songname {
    private String song,songurl;

    public songname(){

    }

    public songname(String song, String songurl) {
        this.song = song;
        this.songurl = songurl;
    }

    public String getSong() {
        return song;
    }

    public void setSong(String song) {
        this.song = song;
    }

    public String getSongurl() {
        return songurl;
    }

    public void setSongurl(String songurl) {
        this.songurl = songurl;
    }
}
