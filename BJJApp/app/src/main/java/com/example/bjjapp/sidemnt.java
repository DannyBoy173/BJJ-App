package com.example.bjjapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class sidemnt extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sidemnt);
    }

    public void goToMain(View view) {
        Intent mainIntent = new Intent(this, MainActivity.class);
        startActivity(mainIntent);
    }

    public void goToSideMntCtrls(View view) {
        Intent intent = new Intent(getApplicationContext(), Notes.class);
        intent.putExtra("Chapter", "Side Mount");
        intent.putExtra("SubChapter", "Controls");
        startActivity(intent);
    }

    public void goToSideMntEsc(View view) {
        Intent intent = new Intent(getApplicationContext(), Notes.class);
        intent.putExtra("Chapter", "Side Mount");
        intent.putExtra("SubChapter", "Escapes");
        startActivity(intent);
    }

    public void goToSideMntCntrs(View view) {
        Intent intent = new Intent(getApplicationContext(), Notes.class);
        intent.putExtra("Chapter", "Side Mount");
        intent.putExtra("SubChapter", "Submission Counters");
        startActivity(intent);
    }

    public void goToSideMntSubs(View view) {
        Intent intent = new Intent(getApplicationContext(), Notes.class);
        intent.putExtra("Chapter", "Side Mount");
        intent.putExtra("SubChapter", "Submissions");
        startActivity(intent);
    }
}
