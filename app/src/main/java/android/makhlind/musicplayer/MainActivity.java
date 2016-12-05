package android.makhlind.musicplayer;

import android.content.Context;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;




public class MainActivity extends AppCompatActivity {

    private MediaPlayer song1;

    private Button playButtonVar;
    private Button pauseButtonVar;
    private Button stopButtonVar;
    private Button rewindButtonVar;
    private Button forwardButtonVar;

    private TextView currentTimeView;
    private TextView finalTimeView;

    private TextView title;
    private TextView author;

    private double currentTimeMS;
    private double finalTimeMS;

    private Handler time = new Handler();

    private SeekBar mySongBarVar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        song1 = MediaPlayer.create(this, R.raw.bargainsinatuxedo);

        title = (TextView) findViewById(R.id.view3);
        author = (TextView) findViewById(R.id.view4);

        finalTimeMS = song1.getDuration();

        mySongBarVar = (SeekBar) findViewById(R.id.seekBar);
        mySongBarVar.setMax((int) finalTimeMS);

        int endMinutes = (int) (finalTimeMS / 1000 / 60);
        int endSeconds = ((int) (finalTimeMS / 1000)) %60;

        finalTimeView = (TextView) findViewById(R.id.view11);
        finalTimeView.setText(endMinutes + " min " + endSeconds + " sec");

        playButtonVar = (Button) findViewById(R.id.view8);
        pauseButtonVar = (Button) findViewById(R.id.view7);
        stopButtonVar = (Button) findViewById(R.id.view6);
        rewindButtonVar = (Button) findViewById(R.id.view5);
        forwardButtonVar = (Button) findViewById(R.id.view9);

        finalTimeMS = song1.getDuration();
        currentTimeMS = song1.getCurrentPosition();

        time.postDelayed(UpdateSongTime, 100);

        MediaMetadataRetriever songInfo = new MediaMetadataRetriever();

        Uri mediapath= Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.bargainsinatuxedo);
        songInfo.setDataSource(this, mediapath);

        String songTitle = songInfo.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE);
        String songAuthor = songInfo.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST);

        title.setText(songTitle);
        author.setText(songAuthor);

    }

    private Runnable UpdateSongTime = new Runnable() {
        public void run() {
            currentTimeMS = song1.getCurrentPosition();

            mySongBarVar.setProgress((int) currentTimeMS);

            int currentMinutes = (int) (currentTimeMS / 1000 / 60);
            int currentSeconds = ((int) (currentTimeMS / 1000)) % 60;

            currentTimeView = (TextView) findViewById(R.id.view10);
            currentTimeView.setText(currentMinutes + " min, " + currentSeconds + " sec");

            //For Rewind / Forward Buttons
            if(currentTimeMS > 5000) {
                rewindButtonVar.setEnabled(true);
            }
            else {
                rewindButtonVar.setEnabled(false);
            }

            if(currentTimeMS < finalTimeMS - 5000) {
                forwardButtonVar.setEnabled(true);
            }
            else {
                forwardButtonVar.setEnabled(false);
            }

            time.postDelayed(this, 100);
        }
    };

    public void playSong(View view) {
        song1.start();

        pauseButtonVar.setEnabled(true);
        stopButtonVar.setEnabled(true);
        playButtonVar.setEnabled(false);

        Context context = getApplicationContext();
        CharSequence text = "The song is playing";
        int duration = Toast.LENGTH_SHORT;
        Toast playMessage= Toast.makeText(context, text, duration);
        playMessage.show();
    }

    public void pauseSong(View view) {
        song1.pause();

        playButtonVar.setEnabled(true);
        pauseButtonVar.setEnabled(false);

        Context context = getApplicationContext();
        CharSequence text = "The song is paused";
        int duration = Toast.LENGTH_SHORT;
        Toast pauseMessage= Toast.makeText(context, text, duration);
        pauseMessage.show();
    }

    public void stopSong(View view) {
        song1.seekTo(0);
        song1.pause();

        playButtonVar.setEnabled(true);
        pauseButtonVar.setEnabled(false);
        stopButtonVar.setEnabled(false);

        Context context = getApplicationContext();
        CharSequence text = "The song is stopped";
        int duration = Toast.LENGTH_SHORT;
        Toast stopMessage= Toast.makeText(context, text, duration);
        stopMessage.show();
    }

    public void rewindSong(View view) {
        song1.seekTo((int) (currentTimeMS - 5000));
    }

    public void forwardSong(View view) {
        song1.seekTo((int) (currentTimeMS + 5000));
    }
}
