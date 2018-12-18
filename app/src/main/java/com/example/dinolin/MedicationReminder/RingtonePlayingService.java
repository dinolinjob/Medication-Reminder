package com.example.dinolin.MedicationReminder;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Dinolin on 08-12-2017.
 */

public class RingtonePlayingService extends Service {
    MediaPlayer media_song;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }
    public int onStartCommand(Intent intent,int flags,int startId){
        Log.i("Local Service","Received start id "+ startId +":"+intent);
        media_song = MediaPlayer.create(this,R.raw.medrem);
        media_song.start();

        return START_NOT_STICKY;
    };

    public void onDestroy(){
        Toast.makeText(this,"On Destroy called",Toast.LENGTH_SHORT).show();
    }
}
