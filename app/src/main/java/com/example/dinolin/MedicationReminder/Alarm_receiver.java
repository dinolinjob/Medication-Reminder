package com.example.dinolin.MedicationReminder;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Dinolin on 07-12-2017.
 */

public class Alarm_receiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("We are in the receiver","Yay");

        Intent service_intent = new Intent(context,RingtonePlayingService.class);
        context.startService(service_intent);
    }
}
