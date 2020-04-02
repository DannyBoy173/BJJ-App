package com.example.bjjapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToMount(View view) {
        Intent mountIntent = new Intent(this, mount.class);
        startActivity(mountIntent);
    }

    public void goToSideMount(View view) {
        Intent mountIntent = new Intent(this, sidemnt.class);
        startActivity(mountIntent);
    }

    public void goToGuard(View view) {
        Intent mountIntent = new Intent(this, guard.class);
        startActivity(mountIntent);
    }

    public void goToHalfGuard(View view) {
        Intent mountIntent = new Intent(this, halfguard.class);
        startActivity(mountIntent);
    }

    public void goToBackMount(View view) {
        Intent mountIntent = new Intent(this, backmount.class);
        startActivity(mountIntent);
    }

    public void goToLegLocks(View view) {
        Intent mountIntent = new Intent(this, leglocks.class);
        startActivity(mountIntent);
    }

    public void goToStanding(View view) {
        Intent mountIntent = new Intent(this, standing.class);
        startActivity(mountIntent);
    }

    public void goToSettings(View view) {
        Intent mountIntent = new Intent(this, settings.class);
        startActivity(mountIntent);
    }

    public void goToCalendar(View view) {
        Intent mountIntent = new Intent(this, calendar.class);
        startActivity(mountIntent);
    }
}
