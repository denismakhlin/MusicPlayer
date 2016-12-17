package android.makhlind.musicplayer;

import android.content.Intent;
import android.media.MediaMetadataRetriever;
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

        int bargainsID = R.raw.bargainsinatuxedo;
        String bargainsTitle= songInfo.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE);
        String bargainsArtist = songInfo.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST);
        bargainsSong; = new SongObject(bargainsID, bargainsTitle,bargainsArtist);

        int arduousSong = R.raw.arduoustask;
        String arduousTitle= songInfo.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE);
        String aurduousArtist = songInfo.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST);
        arduousSong = new SongObject(arduousID, arduousTitle,arduousArtist);
    }

    public void chooseSong(View view) {
        chooseButtonVar.setEnabled(true);
        startActivity(launchSongPlayer);
    }

    public void launchBargains(View view){
        String bargains = String.valueOf(R.raw.bargainsinatuxedo);
        launchPlayer(bargains);
    }

    public void launchArduous(View view){
        String arduous = String.valueOf(R.raw.arduoustask);
        launchPlayer(arduous);
    }

    public void launchPlayer(String songID){
        launchSongPlayer.putExtra("songMessage", songID);

    }
}
