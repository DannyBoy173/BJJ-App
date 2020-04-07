package com.example.bjjapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class backmount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backmount);
    }

    public void goToMain(View view) {
        Intent mainIntent = new Intent(this, MainActivity.class);
        startActivity(mainIntent);
    }

    public void gotoBackCtrls(View view) {
        Intent intent = new Intent(getApplicationContext(), Notes.class);
        intent.putExtra("Chapter", "Back Mount");
        intent.putExtra("SubChapter", "Controls");
        startActivity(intent);
    }

    public void gotoBackSubs(View view) {
        Intent intent = new Intent(getApplicationContext(), Notes.class);
        intent.putExtra("Chapter", "Back Mount");
        intent.putExtra("SubChapter", "Submissions");
        startActivity(intent);
    }

    public void gotoBackCntrs(View view) {
        Intent intent = new Intent(getApplicationContext(), Notes.class);
        intent.putExtra("Chapter", "Back Mount");
        intent.putExtra("SubChapter", "Submission Counters");
        startActivity(intent);
    }
}
