package com.example.bjjapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class guard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guard);
    }

    public void goToMain(View view) {
        Intent mainIntent = new Intent(this, MainActivity.class);
        startActivity(mainIntent);
    }

    public void goToGrdCtrls(View view) {
        Intent intent = new Intent(getApplicationContext(), Notes.class);
        intent.putExtra("Chapter", "Guard");
        intent.putExtra("SubChapter", "Controls");
        startActivity(intent);
    }

    public void goToGrdPass(View view) {
        Intent intent = new Intent(getApplicationContext(), Notes.class);
        intent.putExtra("Chapter", "Guard");
        intent.putExtra("SubChapter", "Passes");
        startActivity(intent);
    }

    public void goToGrdCntrs(View view) {
        Intent intent = new Intent(getApplicationContext(), Notes.class);
        intent.putExtra("Chapter", "Guard");
        intent.putExtra("SubChapter", "Submissions Counters");
        startActivity(intent);
    }

    public void goToGrdSubs(View view) {
        Intent intent = new Intent(getApplicationContext(), Notes.class);
        intent.putExtra("Chapter", "Guard");
        intent.putExtra("SubChapter", "Submissions");
        startActivity(intent);
    }

    public void goToGrdSweeps(View view) {
        Intent intent = new Intent(getApplicationContext(), Notes.class);
        intent.putExtra("Chapter", "Guard");
        intent.putExtra("SubChapter", "Sweeps");
        startActivity(intent);
    }

    public void goToSportGrd(View view) {
        Intent intent = new Intent(getApplicationContext(), Notes.class);
        intent.putExtra("Chapter", "Guard");
        intent.putExtra("SubChapter", "Sport");
        startActivity(intent);
    }
}
