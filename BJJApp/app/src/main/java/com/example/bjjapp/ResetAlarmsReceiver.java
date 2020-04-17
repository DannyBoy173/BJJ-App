package com.example.bjjapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ResetAlarmsReceiver extends BroadcastReceiver {
    private static final String BOOT_COMPLETED = "android.intent.action.BOOT_COMPLETED";
    private static final String QUICKBOOT_POWERON = "android.intent.action.QUICKBOOT_POWERON";

    @Override
    public void onReceive(Context context, Intent intent) {
        //check if the device has been rebooted (then execute a reset alarm service as alarms are deleted on reboot)
        String action = intent.getAction();
        if (action.equals(BOOT_COMPLETED) || action.equals(QUICKBOOT_POWERON)) {
            Intent service = new Intent(context, BootService.class);
            context.startService(service);
        }
    }
}
