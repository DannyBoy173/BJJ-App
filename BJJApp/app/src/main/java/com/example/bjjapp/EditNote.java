package com.example.bjjapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class EditNote extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    private Integer ID;
    private String NoteTitle;
    private String Notes;
    private String Links;
    private EditText TitleText;
    private EditText NoteText;
    private EditText LinkText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        String noteTitle = new String();
        String notes = new String();
        String links = new String();

        //get the chapter ID
        Intent intent = getIntent();
        ID = intent.getIntExtra("NoteID",0);

        //find the edit text fields
        databaseHelper = new DatabaseHelper(this);
        TitleText = (EditText) findViewById(R.id.editNoteTitle);
        NoteText = (EditText) findViewById(R.id.editNoteText);
        LinkText = (EditText) findViewById(R.id.editLinks);

        //get the note data
        Cursor data = databaseHelper.getNote(ID);
        while (data.moveToNext()){
            noteTitle = data.getString(1);
            notes = data.getString(2);
            links = data.getString(3);
        }

        //display correct data in the edit text fields
        TitleText.setText(noteTitle);
        NoteText.setText(notes);
        LinkText.setText(links);
    }

    public void goBack(View view) {
        finish();
    }

    public void discardChanges(View view) {
        //set all text fields to blank
        TitleText.setText("");
        NoteText.setText("");
        LinkText.setText("");
    }

    public void saveChanges(View view) {
        //add the note to the DB
        NoteTitle = (TitleText.getText()).toString();
        Notes = (NoteText.getText()).toString();
        Links = (LinkText.getText()).toString();

        databaseHelper.editData(NoteTitle, Notes, Links, ID);

        finish();
    }
}
