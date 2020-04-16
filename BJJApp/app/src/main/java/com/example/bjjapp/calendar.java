package com.example.bjjapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TimePicker;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

public class calendar extends AppCompatActivity {
    private TimePicker picker;
    private int myCode = 1; //alarm codes

    //shared preference variables
    public static final String PREFS = "sharedPrefs";
    public static final String ALARMS = "StoredAlarmsText";
    public static final String IDS = "StoredAlarmIDs";
    public static final String CODE = "StoredCurrentCode";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        //load the shared prefs to lists
        //they need to be stored as strings then split into lists in order to keep the correct order
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS, MODE_PRIVATE);
        myCode = sharedPreferences.getInt(CODE, 1);
        final String AlarmIDsStr = sharedPreferences.getString(IDS, "");
        ArrayList<String> tempalarmIDs = new ArrayList<String>();
        if (!AlarmIDsStr.isEmpty()){
            tempalarmIDs = new ArrayList<>(Arrays.asList(AlarmIDsStr.split(";")));
        }
        final String datasourceStr = sharedPreferences.getString(ALARMS, "");
        ArrayList<String> tempdatasource = new ArrayList<String>();
        if (!datasourceStr.isEmpty()){
            tempdatasource = new ArrayList<>(Arrays.asList(datasourceStr.split(";")));
        }

        final ArrayList<String> alarmIDs = tempalarmIDs; //list storing alarm IDs
        final ArrayList<String> datasource = tempdatasource; //list storing alarm text (days and times)

        //set up alarm list view
        final ArrayAdapter<String> adapter = new ArrayAdapter<String> (getApplicationContext(),R.layout.alarm_list_item,
                R.id.alarm_list_item_text,datasource);
        ListView lv = (ListView) findViewById(R.id.alarmListView);
        lv.setAdapter(adapter);

        //delete alarm listener
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //find the alarm ID corresponding to the clicked alarm and remove it
                int removeID = Integer.parseInt(alarmIDs.get(position));
                alarmIDs.remove(position);
                //delete the alarm
                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                Intent intent = new Intent(getApplicationContext(), Notification_receiver.class);
                intent.putExtra("AlarmID", removeID);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),removeID,intent,PendingIntent.FLAG_UPDATE_CURRENT);
                alarmManager.cancel(pendingIntent);
                pendingIntent.cancel();

                //remove the clicked alarm from the list view
                datasource.remove(position);
                adapter.notifyDataSetChanged();
                Toast.makeText(getApplicationContext(), "Alarm Deleted", Toast.LENGTH_SHORT).show();

                //delete the alarm from the shared preferences by saving the new lists to shared prefs
                saveData(datasource, alarmIDs, myCode);
            }
        });

        //set up time picker
        picker = (TimePicker) findViewById(R.id.timePicker);
        picker.setIs24HourView(true);

        //set up day picker
        final NumberPicker dayPicker = findViewById(R.id.dayPicker);
        String[] days = new String[]{"Mon", "Tues", "Weds", "Thurs", "Fri", "Sat", "Sun"};
        dayPicker.setMinValue(0);
        dayPicker.setMaxValue(days.length-1);
        dayPicker.setDisplayedValues(days);

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
                String dayAsStr = "";
                switch(day) {
                    case 0:
                        alarmTime.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
                        dayAsStr = "Monday";
                        break;
                    case 1:
                        alarmTime.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
                        dayAsStr = "Tuesday";
                        break;
                    case 2:
                        alarmTime.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
                        dayAsStr = "Wednesday";
                        break;
                    case 3:
                        alarmTime.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
                        dayAsStr = "Thursday";
                        break;
                    case 4:
                        alarmTime.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
                        dayAsStr = "Friday";
                        break;
                    case 5:
                        alarmTime.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
                        dayAsStr = "Saturday";
                        break;
                    case 6:
                        alarmTime.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
                        dayAsStr = "Sunday";
                        break;
                }

                // check if alarm time is in the past (it will go off straight away if in the past) and add a week if so
                if (alarmTime.before(currentTime)) {
                    alarmTime.add(Calendar.DAY_OF_MONTH, 7);
                }

                //intent to take us to broadcast receiver
                Intent intent = new Intent(getApplicationContext(), Notification_receiver.class);
                intent.putExtra("AlarmID", myCode);

                //create pending intent from the intent above
                PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),myCode,intent,PendingIntent.FLAG_UPDATE_CURRENT);
                Toast.makeText(getApplicationContext(), "Alarm Set", Toast.LENGTH_SHORT).show();
                alarmIDs.add(Integer.toString(myCode)); //add alarm ID to list
                myCode+=1;

                //set up a weekly alarm for the time selected, takes pending intent as a parameter. Triggers notification weekly
                int interval = 1000 * 60 * 60 * 24 * 7;
                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,alarmTime.getTimeInMillis(),interval,pendingIntent);

                //add the alarm to the list view
                String minStr = Integer.toString(min);
                if (min < 10){
                    minStr = "0"+ minStr;
                }
                String hourStr = Integer.toString(hour);
                if (hour < 10){
                    hourStr = "0"+ hourStr;
                }
                datasource.add(dayAsStr + " - " + hourStr + ":" + minStr);
                adapter.notifyDataSetChanged();

                //save the new alarm to the shared preferences
                saveData(datasource, alarmIDs, myCode);
            }
        });

        }


    private void saveData(ArrayList<String> datasource, ArrayList<String> alarmIDs, int CurrentCode) {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        //join the lists as a string to be saved in shared prefs
        String datasourceStr = TextUtils.join(";", datasource);
        String alarmIDsStr = TextUtils.join(";", alarmIDs);
        editor.putInt(CODE, CurrentCode);
        editor.putString(ALARMS, datasourceStr);
        editor.putString(IDS, alarmIDsStr);
        editor.commit();
    }

    public void goToMain(View view) {
        finish();
    }
}
