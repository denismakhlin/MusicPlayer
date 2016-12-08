package android.makhlind.musicplayer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class SongPicker extends AppCompatActivity {

    private Button chooseButtonVar;
    Intent launchSongPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_picker);
        chooseButtonVar = (Button) findViewById(R.id.chooseButton1);
        launchSongPlayer = new Intent(this, MainActivity.class);
    }

    public void chooseSong(View view) {
        chooseButtonVar.setEnabled(true);
        startActivity(launchSongPlayer);
    }
}
