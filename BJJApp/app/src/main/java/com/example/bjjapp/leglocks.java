package com.example.bjjapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class leglocks extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leglocks);
    }

    public void goToMain(View view) {
        Intent mainIntent = new Intent(this, MainActivity.class);
        startActivity(mainIntent);
    }

    public void goToLegStraight(View view) {
        Intent intent = new Intent(getApplicationContext(), Notes.class);
        intent.putExtra("Chapter", "Leg Locks");
        intent.putExtra("SubChapter", "Straight Foot Locks");
        startActivity(intent);
    }

    public void goToLegToe(View view) {
        Intent intent = new Intent(getApplicationContext(), Notes.class);
        intent.putExtra("Chapter", "Leg Locks");
        intent.putExtra("SubChapter", "Toe Holds");
        startActivity(intent);
    }

    public void goToLegKnee(View view) {
        Intent intent = new Intent(getApplicationContext(), Notes.class);
        intent.putExtra("Chapter", "Leg Locks");
        intent.putExtra("SubChapter", "Knee Locks");
        startActivity(intent);
    }

    public void goToLegHeel(View view) {
        Intent intent = new Intent(getApplicationContext(), Notes.class);
        intent.putExtra("Chapter", "Leg Locks");
        intent.putExtra("SubChapter", "Heel Hooks");
        startActivity(intent);
    }
}
