package jp.djembient.thecrossofrhythm;

import static android.telephony.PhoneStateListener.LISTEN_CALL_STATE;
import static androidx.core.app.ServiceCompat.startForeground;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Vibrator;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import jp.djembient.thecrossofrhythm.R.id;
import jp.djembient.thecrossofrhythm.R.layout;

public final class MainActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer00;
    private MediaPlayer mediaPlayer01;
    private MediaPlayer mediaPlayer02;
    private MediaPlayer mediaPlayer03;
    private MediaPlayer mediaPlayer04;
    private MediaPlayer mediaPlayer05;
    private MediaPlayer mediaPlayer06;
    private Button buttonStart;
    private Button buttonStop;
    private Button buttonBGM01;
    private Button buttonBGM02;
    private Button buttonBGM03;
    private Button buttonBGM04;
    private Button buttonBGM05;
    private Button buttonBGM06;
    private Button buttonBGM07;
    private SeekBar seekBar01;
    private SeekBar seekBar02;
    private TextView textView01;
    private TextView textView02;
    private TextView textView03;
    private Vibrator vib;
    private long[] pattern01 = new long[]{0L, 250L, 62L, 125L, 62L, 125L, 125L, 125L};
    private long[] pattern02 = new long[]{0L, 250L, 125L, 250L};

    //Handle incoming phone calls
//    private boolean ongoingCall = false;
//    private PhoneReceiver phoneStateListener;
//    private TelephonyManager telephonyManager;
    private static final String CHANNEL_ID = "soundmanagerservice_notification_channel";
    private Intent i;
    private BindService bindService;
    private ServiceConnection conn = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            BindService.LocalBinder binder = (BindService.LocalBinder) service;
            bindService = binder.getService();
            mediaPlayer00 = bindService.getMediaPlayer00();
            mediaPlayer00.setVolume(0.25f,0.25f);
            mediaPlayer00.seekTo(0);
            mediaPlayer01 = bindService.getMediaPlayer01();
            mediaPlayer02 = bindService.getMediaPlayer02();
            mediaPlayer03 = bindService.getMediaPlayer03();
            mediaPlayer04 = bindService.getMediaPlayer04();
            mediaPlayer05 = bindService.getMediaPlayer05();
            mediaPlayer06 = bindService.getMediaPlayer06();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

            bindService = null;
        }
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);

        buttonStart = (Button) findViewById(id.play);
        buttonStop = (Button) findViewById(id.pause);
        buttonBGM01 = (Button) findViewById(id.bgm01);
        buttonBGM02 = (Button) findViewById(id.bgm02);
        buttonBGM03 = (Button) findViewById(id.bgm03);
        buttonBGM04 = (Button) findViewById(id.bgm04);
        buttonBGM05 = (Button) findViewById(id.bgm05);
        buttonBGM06 = (Button) findViewById(id.bgm06);
        buttonBGM07 = (Button) findViewById(id.bgm07);
        textView01 = (TextView) findViewById(id.text_view01);
        textView02 = (TextView) findViewById(id.text_view02);
        textView03 = (TextView) findViewById(id.text_view03);
        textView03.setText((CharSequence) "交差するリズム\nby\nDj Akira Sada");
        seekBar01 = (SeekBar) findViewById(id.seekbar01);
        seekBar02 = (SeekBar) findViewById(id.seekbar02);
        seekBar01.setProgress(100);
        seekBar01.setMax(100);
        seekBar02.setProgress(100);
        seekBar02.setMax(100);
        vib = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        i = new Intent((Context)this, BindService.class);
        bindService(i, conn, Service.BIND_AUTO_CREATE);

        Log.v("xxx", "onCreate");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        unbindService(conn);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(200);
    }

    protected void onStop() {

        super.onStop();
//        manager.listen(myPhoneStateListener,PhoneStateListener.LISTEN_NONE);
        }

    protected void onRestart() {
        super.onRestart();
        Log.v("xxx", "onRestart");
    }

    public void onStart() {
        super.onStart();
        Log.v("xxx", "onStart");
    }

    protected void onResume() {
        super.onResume();

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vib.vibrate(pattern01, -1);
                audioPlay();
            }
        });

        buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vib.vibrate(pattern02, -1);
                audioStop();
            }
        });

        buttonBGM01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vib.vibrate(500);
                mediaPlayerCheck();
                mediaPlayer01.start();
                mediaPlayer01.setLooping(true);
            }
        });

        buttonBGM02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vib.vibrate(500);
                mediaPlayerCheck();
                mediaPlayer02.start();
                mediaPlayer02.setLooping(true);
            }
        });

        buttonBGM03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vib.vibrate(500);
                mediaPlayerCheck();
                mediaPlayer03.start();
                mediaPlayer03.setLooping(true);
            }
        });

        buttonBGM04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vib.vibrate(500);
                mediaPlayerCheck();
                mediaPlayer04.start();
                mediaPlayer04.setLooping(true);
            }
        });

        buttonBGM05.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vib.vibrate(500);
                mediaPlayerCheck();
                mediaPlayer05.start();
                mediaPlayer05.setLooping(true);
            }
        });
        buttonBGM06.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vib.vibrate(500);
                mediaPlayerCheck();
                mediaPlayer06.start();
                mediaPlayer06.setLooping(true);
            }
        });
        buttonBGM07.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayerCheck();
            }
        });

        seekBar01.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // つまみが変更された時に処理が実行される
                mediaPlayer00.setVolume((float) seekBar01.getProgress() / (float) 100 / (float) 4, (float) seekBar01.getProgress() / (float) 100 / (float) 4);
                textView01.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // ユーザーがタップ開始した時に処理が実行される
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // ユーザーがタップ終了した時に処理が実行される
            }
        });

        seekBar02.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // つまみが変更された時に処理が実行される
                mediaPlayer01.setVolume((float) seekBar02.getProgress() / 100, (float) seekBar02.getProgress() / 100);
                mediaPlayer02.setVolume((float) seekBar02.getProgress() / 100, (float) seekBar02.getProgress() / 100);
                mediaPlayer03.setVolume((float) seekBar02.getProgress() / 100, (float) seekBar02.getProgress() / 100);
                mediaPlayer04.setVolume((float) seekBar02.getProgress() / 100, (float) seekBar02.getProgress() / 100);
                mediaPlayer05.setVolume((float) seekBar02.getProgress() / 100, (float) seekBar02.getProgress() / 100);
                mediaPlayer06.setVolume((float) seekBar02.getProgress() / 100, (float) seekBar02.getProgress() / 100);
                textView02.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // ユーザーがタップ開始した時に処理が実行される
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // ユーザーがタップ終了した時に処理が実行される
            }
        });
        //Handle incoming phone calls
/*        private void callStateListener () {
            // Get the telephony manager
            telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
            //Starting listening for PhoneState changes
            phoneStateListener = new PhoneStateListener() {
                @Override
                public void onCallStateChanged(int state, String incomingNumber) {
                    switch (state) {
                        //if at least one call exists or the phone is ringing
                        //pause the MediaPlayer
                        case TelephonyManager.CALL_STATE_OFFHOOK:
                        case TelephonyManager.CALL_STATE_RINGING:
                            audioStop();
                            ongoingCall = true;
                            break;
                        case TelephonyManager.CALL_STATE_IDLE:
                            if (ongoingCall) {
                                ongoingCall = false;
                                audioPlay();
                            }
                            break;
                    }
                }
            };
            // Register the listener with the telephony manager
            // Listen for changes to the device call state.
            telephonyManager.listen(phoneStateListener,
                    PhoneStateListener.LISTEN_CALL_STATE);
        }*/
        Log.v("xxx", "onResume");
    }

    protected void onDestroy() {
        super.onDestroy();
        unbindService(conn);
    }
        private void mediaPlayerCheck() {
        if (mediaPlayer01.isPlaying()) {
            mediaPlayer01.pause();
            mediaPlayer01.seekTo(0);
        }

        if (mediaPlayer02.isPlaying()) {
            mediaPlayer02.pause();
            mediaPlayer02.seekTo(0);
        }

        if (mediaPlayer03.isPlaying()) {
            mediaPlayer03.pause();
            mediaPlayer03.seekTo(0);
        }

        if (mediaPlayer04.isPlaying()) {
            mediaPlayer04.pause();
            mediaPlayer04.seekTo(0);
        }

        if (mediaPlayer05.isPlaying()) {
            mediaPlayer05.pause();
            mediaPlayer05.seekTo(0);
        }

        if (mediaPlayer06.isPlaying()) {
            mediaPlayer06.pause();
            mediaPlayer06.seekTo(0);
        }
    }
    private void audioPlay() {
        if (mediaPlayer00.isPlaying()) {
            mediaPlayer00.pause();
        } else {
            try {
                mediaPlayer00.setOnPreparedListener(new PlayerPreparedListener());
                mediaPlayer00.setOnCompletionListener(new PlayerCompletionListener());
//                mediaPlayer00.prepareAsync();
            } catch (IllegalStateException ex) {
                Log.e("xxx", "", ex);
            }
            Log.v("xxx", "O.K.");
            mediaPlayer00.start();

            Intent intent = new Intent( this, NotificationService.class);
            // サービス を 起動。
            this.startForegroundService(intent);
        }
    }

    private void audioStop() {
        if(mediaPlayer00.isPlaying()) {
            mediaPlayer00.pause();
            mediaPlayer00.seekTo(0);
            notification_finish();
        } else {
            mediaPlayer00.seekTo(0);
            notification_finish();
            Log.v("xxx", "N.G.");
        }
    }

    private class PlayerPreparedListener implements MediaPlayer.OnPreparedListener {
        @Override
        public void onPrepared(MediaPlayer mp) {
        }
    }
    private class PlayerCompletionListener implements MediaPlayer.OnCompletionListener {
        @Override public void onCompletion( MediaPlayer mp) {
            audioStop();
            mediaPlayerCheck();
            notification_finish();
        }
    }
    private void notification_finish () {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, CHANNEL_ID);
        // 通知 エリア に 表示 さ れる アイコン を 設定。
        builder.setSmallIcon(android.R.drawable.stat_sys_headset);
        // 通知 ドロワー での 表示 タイトル を 設定。
        builder.setContentTitle(getString(R.string.msg_notification_title_finish));
        // 通知 ドロワー での 表示 メッセージ を 設定。
        builder.setContentText(getString(R.string.msg_notification_text_finish));
        // Builder から Notification オブジェクト を 生成。
        Notification notification = builder.build();
        // NotificationManagerCompat オブジェクト を 取得。
//            NotificationManagerCompat manager = NotificationManagerCompat.from(MainActivity.this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            String name = getString(R.string.notification_channel_name);
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
            // 通知。
            manager.notify(200, notification);
        }
    }
/*    @Override
    public void onCallStateChanged(int state, String incomingNumber) {
        super.onCallStateChanged(state, incomingNumber);
        switch (state) {
            case TelephonyManager.CALL_STATE_OFFHOOK:
            case TelephonyManager.CALL_STATE_RINGING:
                // Here you can perform your task while phone is ringing.
                break;
            case TelephonyManager.CALL_STATE_IDLE:
                break;
        }
    }*/
}
