package com.example.bjjapp;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

public class BootService extends IntentService {

    //shared preference variables
    public static final String PREFS = "sharedPrefs";
    public static final String ALARMS = "StoredAlarmsText";
    public static final String IDS = "StoredAlarmIDs";

    public BootService() {
        super("BootService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        //code to reset the alarms from shared prefs after reboot has been detected, performed in the background

        //get the alarms from the shared preferences
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS, MODE_PRIVATE);
        final String AlarmIDsStr = sharedPreferences.getString(IDS, "");
        ArrayList<String> tempalarmIDs = new ArrayList<String>();
        if (!AlarmIDsStr.isEmpty()){
            tempalarmIDs = new ArrayList<>(Arrays.asList(AlarmIDsStr.split(";")));
        }
        final String AlarmsStr = sharedPreferences.getString(ALARMS, "");
        ArrayList<String> tempalarmsstr = new ArrayList<String>();
        if (!AlarmsStr.isEmpty()){
            tempalarmsstr = new ArrayList<>(Arrays.asList(AlarmsStr.split(";")));
        }

        final ArrayList<String> alarmIDs = tempalarmIDs; //list storing alarm IDs
        final ArrayList<String> Alarms = tempalarmsstr; //list storing alarm text (days and times)

        //get current time
        Calendar currentTime = Calendar.getInstance();

        int numAlarms = Alarms.size();

        //reset the alarms
        for (int i = 0; i < numAlarms; i++) {
            int alarmID = Integer.parseInt(alarmIDs.get(i));
            int hour;
            int min;
            String day;

            //get the alarm text from shared pref and parse it into day, hour, min
            String Alarmtxt = Alarms.get(i);
            ArrayList<String> AlarmSplit = new ArrayList<String>();
            AlarmSplit = new ArrayList<>(Arrays.asList(Alarmtxt.split(" - ")));
            day = AlarmSplit.get(0); //get the day from the list
            String time = AlarmSplit.get(1); //get the time from the list

            ArrayList<String> TimeSplit = new ArrayList<String>();
            TimeSplit = new ArrayList<>(Arrays.asList(time.split(":")));
            hour = Integer.parseInt(TimeSplit.get(0)); //get the hour
            min = Integer.parseInt(TimeSplit.get(1)); //get the min

            //set alarms time
            Calendar alarmTime = Calendar.getInstance();
            alarmTime.set(Calendar.HOUR_OF_DAY, hour);
            alarmTime.set(Calendar.MINUTE, min);

            //switch case for the day picker to set alarm day
            switch(day) {
                case "Monday":
                    alarmTime.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
                    break;
                case "Tuesday":
                    alarmTime.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
                    break;
                case "Wednesday":
                    alarmTime.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
                    break;
                case "Thursday":
                    alarmTime.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
                    break;
                case "Friday":
                    alarmTime.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
                    break;
                case "Saturday":
                    alarmTime.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
                    break;
                case "Sunday":
                    alarmTime.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
                    break;
            }

            // check if alarm time is in the past (it will go off straight away if in the past) and add a week if so
            if (alarmTime.before(currentTime)) {
                alarmTime.add(Calendar.DAY_OF_MONTH, 7);
            }

            //intent to take us to broadcast receiver
            Intent alarmIntent = new Intent(getApplicationContext(), Notification_receiver.class);
            alarmIntent.putExtra("AlarmID", alarmID);

            //create pending intent from the intent above
            PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), alarmID, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            Toast.makeText(getApplicationContext(), "Alarm Set", Toast.LENGTH_SHORT).show();
            alarmIDs.add(Integer.toString(alarmID)); //add alarm ID to list

            //set up a weekly alarm for the time selected, takes pending intent as a parameter. Triggers notification weekly
            int interval = 1000 * 60 * 60 * 24 * 7;
            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, alarmTime.getTimeInMillis(), interval, pendingIntent);
        }


        //stop the service once it is complete
        Intent service = new Intent(this, BootService.class);
        stopService(service);
    }
}
