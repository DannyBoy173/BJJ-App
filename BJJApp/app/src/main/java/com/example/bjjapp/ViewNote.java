package com.example.bjjapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ViewNote extends AppCompatActivity {
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_note);
        databaseHelper = new DatabaseHelper(this);
        String noteTitle = new String();
        String notes = new String();
        String links = new String();

        //get the intent data to display the correct note (the note ID)
        Intent intent = getIntent();
        Integer ID = intent.getIntExtra("NoteID",0);

        //get the note data
        Cursor data = databaseHelper.getNote(ID);
        while (data.moveToNext()){
            noteTitle = data.getString(1);
            notes = data.getString(2);
            links = data.getString(3);
        }

        //display correct data in the text fields
        TextView title = (TextView)findViewById(R.id.note_title);
        title.setText(noteTitle);
        TextView noteText = (TextView)findViewById(R.id.notesText);
        noteText.setText(notes);
        TextView linkText = (TextView)findViewById(R.id.hyperlinks);
        linkText.setText(links);
    }

    public void goBack(View view) {
        finish();
    }
}
