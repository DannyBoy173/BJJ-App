package com.example.bjjapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class mount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mount);
    }

    public void goToMain(View view) {
        Intent mainIntent = new Intent(this, MainActivity.class);
        startActivity(mainIntent);
    }

    public void goToMntCtrls(View view) {
        Intent intent = new Intent(getApplicationContext(), Notes.class);
        intent.putExtra("Chapter", "Mount");
        intent.putExtra("SubChapter", "Controls");
        startActivity(intent);
    }

    public void goToMntEsc(View view) {
        Intent intent = new Intent(getApplicationContext(), Notes.class);
        intent.putExtra("Chapter", "Mount");
        intent.putExtra("SubChapter", "Escapes");
        startActivity(intent);
    }

    public void goToMntCntrs(View view) {
        Intent intent = new Intent(getApplicationContext(), Notes.class);
        intent.putExtra("Chapter", "Mount");
        intent.putExtra("SubChapter", "Submission Counters");
        startActivity(intent);
    }

    public void goToMntSubs(View view) {
        Intent intent = new Intent(getApplicationContext(), Notes.class);
        intent.putExtra("Chapter", "Mount");
        intent.putExtra("SubChapter", "Submissions");
        startActivity(intent);
    }
}
