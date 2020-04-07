package com.example.bjjapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class standing extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standing);
    }

    public void goToMain(View view) {
        Intent mainIntent = new Intent(this, MainActivity.class);
        startActivity(mainIntent);
    }

    public void goToStdClinch(View view) {
        Intent intent = new Intent(getApplicationContext(), Notes.class);
        intent.putExtra("Chapter", "Standing");
        intent.putExtra("SubChapter", "Clinch");
        startActivity(intent);
    }

    public void goToStdTakedowns(View view) {
        Intent intent = new Intent(getApplicationContext(), Notes.class);
        intent.putExtra("Chapter", "Standing");
        intent.putExtra("SubChapter", "Takedowns");
        startActivity(intent);
    }

    public void goToStdWeaponDef(View view) {
        Intent intent = new Intent(getApplicationContext(), Notes.class);
        intent.putExtra("Chapter", "Standing");
        intent.putExtra("SubChapter", "Weapon Defences");
        startActivity(intent);
    }

    public void goToStdFrntDef(View view) {
        Intent intent = new Intent(getApplicationContext(), Notes.class);
        intent.putExtra("Chapter", "Standing");
        intent.putExtra("SubChapter", "Front Attack Defences");
        startActivity(intent);
    }

    public void goToStdRearDef(View view) {
        Intent intent = new Intent(getApplicationContext(), Notes.class);
        intent.putExtra("Chapter", "Standing");
        intent.putExtra("SubChapter", "Rear Attack Defences");
        startActivity(intent);
    }
}
