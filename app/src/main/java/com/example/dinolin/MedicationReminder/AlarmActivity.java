package com.example.dinolin.MedicationReminder;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

import static java.util.Calendar.getInstance;

/**
 * Created by Dinolin on 07-12-2017.
 */

public class AlarmActivity extends AppCompatActivity{
   AlarmManager alarm_manager;
   TimePicker alarm_time_picker;
   TextView update_text;
   Context context;
   PendingIntent pending_intent;
    @Override
   protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm_activity);
        setTitle("Setting reminder");
        this.context = this;

        alarm_manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarm_time_picker = (TimePicker) findViewById(R.id.time_picker);
        update_text = (TextView) findViewById(R.id.update_text);

        final Calendar calendar = Calendar.getInstance();

        Button set_button = (Button) findViewById(R.id.set_button);

        final Intent my_intent = new Intent(this.context,Alarm_receiver.class);

        set_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.set(Calendar.HOUR_OF_DAY,alarm_time_picker.getHour());
                calendar.set(Calendar.MINUTE,alarm_time_picker.getMinute());

                int hour = alarm_time_picker.getHour();
                int minute = alarm_time_picker.getMinute();

                String hour_string = String.valueOf(hour);
                String minute_string = String.valueOf(minute);

                if(hour>12){
                    hour_string = String.valueOf(hour - 12);
                }
                if(minute<10){
                    minute_string = "0"+String.valueOf(minute);
                }
               set_alarm_text("Alarm set to : "+ hour_string +":"+ minute_string);

                pending_intent = PendingIntent.getBroadcast(AlarmActivity.this,0,my_intent,PendingIntent.FLAG_UPDATE_CURRENT);

                alarm_manager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pending_intent);
            }

            private void set_alarm_text(String s) {
                update_text.setText(s);
            }
        });

        Button cancel_button = (Button) findViewById(R.id.cancel_button);

       cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_alarm_text("Alarm OFF");
                alarm_manager.cancel(pending_intent);
            }

            private void set_alarm_text(String s) {
                update_text.setText(s);
            }
        });
    }
}
