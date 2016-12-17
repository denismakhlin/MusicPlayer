package android.makhlind.musicplayer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SongObject {

    public int songID;
    public String title;
    public String artist;

    public SongObject(int songID, String title, String artist) {
        this.songID = songID;
        this.title = title;
        this.artist = artist;
    }
}