package com.example.bjjapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormatSymbols;
import java.util.Calendar;

public class calendar extends AppCompatActivity {
    private TimePicker picker;
    private int myCode = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        //set up time picker
        picker = (TimePicker) findViewById(R.id.timePicker);
        picker.setIs24HourView(true);

        //set up day picker
        final NumberPicker dayPicker = findViewById(R.id.dayPicker);
        String[] data = new String[]{"Mon", "Tues", "Weds", "Thurs", "Fri", "Sat", "Sun"};
        dayPicker.setMinValue(0);
        dayPicker.setMaxValue(data.length-1);
        dayPicker.setDisplayedValues(data);

        findViewById(R.id.set_notification_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int hour = picker.getHour();
                int min = picker.getMinute();
                int day = dayPicker.getValue();

                //get current time
                Calendar currentTime = Calendar.getInstance();

                //set alarms time
                Calendar alarmTime = Calendar.getInstance();
                alarmTime.set(Calendar.HOUR_OF_DAY,hour);
                alarmTime.set(Calendar.MINUTE,min);
                //switch case for the day picker to set alarm day
                switch(day) {
                    case 0:
                        alarmTime.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
                        break;
                    case 1:
                        alarmTime.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
                        break;
                    case 2:
                        alarmTime.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
                        break;
                    case 3:
                        alarmTime.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
                        break;
                    case 4:
                        alarmTime.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
                        break;
                    case 5:
                        alarmTime.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
                        break;
                    case 6:
                        alarmTime.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
                        break;
                }

                // check if alarm time is in the future (it will go off straight away if in the past)
                if (currentTime.getTimeInMillis() <  alarmTime.getTimeInMillis()) {
                    // nothing to do - time of alarm is in the future
                } else {
                    int dayDiff = (alarmTime.get(Calendar.DAY_OF_WEEK) - currentTime.get(Calendar.DAY_OF_WEEK)) % 7;
                    if (dayDiff == 0) {
                        //It's the same day but alarm was earlier, so set for next week
                        dayDiff = 7;
                    }
                    alarmTime.add(Calendar.DAY_OF_MONTH, dayDiff);
                }

                //intent to take us to broadcast receiver
                Intent intent = new Intent(getApplicationContext(), Notification_receiver.class);
                intent.putExtra("AlarmID", myCode);

                //create pending intent from the intent above
                PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),myCode,intent,PendingIntent.FLAG_UPDATE_CURRENT);
                Toast.makeText(getApplicationContext(), "Alarm Set - Code: " + Integer.toString(myCode) +
                        "\nFor:  " + Integer.toString(day) + " " +
                        Integer.toString(hour) + ":" + Integer.toString(min), Toast.LENGTH_LONG).show();
                myCode+=1;

                //set up a weekly alarm for the time selected, takes pending intent as a parameter. Triggers notification weekly
                int interval = 1000 * 60 * 60 * 24 * 7;
                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,alarmTime.getTimeInMillis(),interval,pendingIntent);
            }
        });

        findViewById(R.id.cancel_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                Intent intent = new Intent(getApplicationContext(), Notification_receiver.class);
                intent.putExtra("AlarmID", 1);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),1,intent,PendingIntent.FLAG_UPDATE_CURRENT);
                alarmManager.cancel(pendingIntent);
                pendingIntent.cancel();
            }
        });
        }



    public void goToMain(View view) {
        Intent mainIntent = new Intent(this, MainActivity.class);
        startActivity(mainIntent);
    }
}
