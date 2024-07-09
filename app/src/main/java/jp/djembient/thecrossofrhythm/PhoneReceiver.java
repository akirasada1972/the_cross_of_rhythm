/*package jp.djembient.thecrossofrhythm;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class PhoneReceiver extends PhoneStateListener{
    Context context;
    public PhoneReceiver(Context context){
        this.context = context;
    }
    private BindService bindService;
    private ServiceConnection conn = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            BindService.LocalBinder binder = (BindService.LocalBinder) service;
            bindService = binder.getService();
            mediaPlayer00 = bindService.getMediaPlayer00();
            mediaPlayer00.setVolume(0.25f,0.25f);
            mediaPlayer01 = bindService.getMediaPlayer01();
            mediaPlayer02 = bindService.getMediaPlayer02();
            mediaPlayer03 = bindService.getMediaPlayer03();
            mediaPlayer04 = bindService.getMediaPlayer04();
            mediaPlayer05 = bindService.getMediaPlayer05();
            mediaPlayer06 = bindService.getMediaPlayer06();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

//            bindService = null;
        }
    };

    Intent i = new Intent((Context)this, BindService.class);
    bindService(i, conn, Service.BIND_AUTO_CREATE);

    @Override
    public void onCallStateChanged(int state, String incomingNumber){
        super.onCallStateChanged(state, incomingNumber);

//        Toast.makeText(context, "onCallStateChanged state" + state + "incomingNumber=" + incomingNumber, Toast.LENGTH_LONG).show();

        switch(state){
            case TelephonyManager.CALL_STATE_IDLE:
//                Toast.makeText(context, "idle", Toast.LENGTH_LONG).show();
                break;
            case TelephonyManager.CALL_STATE_RINGING:
//                Toast.makeText(context, "ringing", Toast.LENGTH_LONG).show();
                break;
            case TelephonyManager.CALL_STATE_OFFHOOK:
//                Toast.makeText(context, "offhook", Toast.LENGTH_LONG).show();
                break;
        }
    }
}*/