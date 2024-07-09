package jp.djembient.thecrossofrhythm;

import static android.content.Intent.getIntent;
import static android.content.Intent.getIntentOld;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import java.io.IOException;

public class BindService extends Service {
    
    private MediaPlayer mediaPlayer00;
    private MediaPlayer mediaPlayer01;
    private MediaPlayer mediaPlayer02;
    private MediaPlayer mediaPlayer03;
    private MediaPlayer mediaPlayer04;
    private MediaPlayer mediaPlayer05;
    private MediaPlayer mediaPlayer06;
    private final IBinder binder = (IBinder)(new LocalBinder());
    public final MediaPlayer getMediaPlayer00() {
        return mediaPlayer00;
    }
    public final MediaPlayer getMediaPlayer01() {
        return mediaPlayer01;
    }
    public final MediaPlayer getMediaPlayer02() {
        return mediaPlayer02;
    }
    public final MediaPlayer getMediaPlayer03() {
        return mediaPlayer03;
    }
    public final MediaPlayer getMediaPlayer04() {
        return mediaPlayer04;
    }
    public final MediaPlayer getMediaPlayer05() {
        return mediaPlayer05;
    }
    public final MediaPlayer getMediaPlayer06() {
        return mediaPlayer06;
    }

    public void onCreate() {
        super.onCreate();

        String url = "https://www.djembient.jp/joint.mp3";
        mediaPlayer00 = new MediaPlayer();
        mediaPlayer00.setAudioAttributes(
                new AudioAttributes
                        .Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .build());;
        try {
            mediaPlayer00.setDataSource(url);
            mediaPlayer00.prepare();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        mediaPlayer00 = MediaPlayer.create((Context) this, R.raw.joint);
        mediaPlayer01 = MediaPlayer.create((Context) this, R.raw.stream_day);
        mediaPlayer02 = MediaPlayer.create((Context) this, R.raw.stream_night);
        mediaPlayer03 = MediaPlayer.create((Context) this, R.raw.thunder);
        mediaPlayer04 = MediaPlayer.create((Context) this, R.raw.frog);
        mediaPlayer05 = MediaPlayer.create((Context) this, R.raw.night);
        mediaPlayer06 = MediaPlayer.create((Context) this, R.raw.coast);
        }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.v("xxx", "stopService");
        return START_NOT_STICKY;
    }

    public void onDestroy() {
        super.onDestroy();
        Log.v("xxx","onDestroy_bind");
        mediaPlayer00.release();
        mediaPlayer01.release();
        mediaPlayer02.release();
        mediaPlayer03.release();
        mediaPlayer04.release();
        mediaPlayer05.release();
        mediaPlayer06.release();
        stopSelf();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public class LocalBinder extends Binder {
        BindService getService() {
            return BindService.this;
        }
    }
}