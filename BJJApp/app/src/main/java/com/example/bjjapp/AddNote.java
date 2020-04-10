package com.example.bjjapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddNote extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    private Integer chapterID;
    private String NoteTitle;
    private String Notes;
    private String Links;
    private EditText TitleText;
    private EditText NoteText;
    private EditText LinkText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        //get the chapter ID
        Intent intent = getIntent();
        chapterID = intent.getIntExtra("chapterID",0);

        //find the edit text fields
        databaseHelper = new DatabaseHelper(this);
        TitleText = (EditText) findViewById(R.id.editNoteTitle);
        NoteText = (EditText) findViewById(R.id.editNoteText);
        LinkText = (EditText) findViewById(R.id.editLinks);
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

        databaseHelper.addData(NoteTitle, Notes, Links, chapterID);

        finish();
    }
}
