package com.example.bjjapp;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

public class Notification_receiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        //get the alarmID
        Integer AlarmID = intent.getIntExtra("AlarmID",0);

        //create the notification
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(android.R.drawable.ic_popup_reminder)
                .setContentTitle("BJJ Training Reminder")
                .setContentText("You have training in 1 hour")
                .setAutoCancel(true);

        notificationManager.notify(AlarmID,builder.build());

    }
}

