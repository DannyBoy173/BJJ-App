package com.example.bjjapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;

public class Notes extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    private ListView listView;
    private ImageButton btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        btnAdd = (ImageButton) findViewById(R.id.add_btn);
        databaseHelper = new DatabaseHelper(this);
        listView = (ListView) findViewById(R.id.notesListView);

        //get the intent data to display the correct notes
        Intent intent = getIntent();
        String chapter = intent.getStringExtra("Chapter");
        String subchapter = intent.getStringExtra("SubChapter");

        //display correct title
        TextView title = (TextView)findViewById(R.id.notes_title);
        title.setText(chapter + "\n" + subchapter);

        //switch statement to identify the ID which relates to the current chapter we're in
        //this chapter ID can then be used to query the Db to find relevant notes
        String wholeChapter = chapter + " " + subchapter;
        final Integer chapterID;
        switch (wholeChapter){
            case "Mount Controls":
                chapterID = 1;
                break;
            case "Mount Escapes":
                chapterID = 2;
                break;
            case "Mount Submission Counters":
                chapterID = 3;
                break;
            case "Mount Submissions":
                chapterID = 4;
                break;
            case "Side Mount Controls":
                chapterID = 5;
                break;
            case "Side Mount Escapes":
                chapterID = 6;
                break;
            case "Side Mount Submission Counters":
                chapterID = 7;
                break;
            case "Side Mount Submissions":
                chapterID = 8;
                break;
            case "Guard Controls":
                chapterID = 9;
                break;
            case "Guard Passes":
                chapterID = 10;
                break;
            case "Guard Submission Counters":
                chapterID = 11;
                break;
            case "Guard Submissions":
                chapterID = 12;
                break;
            case "Guard Sweeps":
                chapterID = 13;
                break;
            case "Guard Sport":
                chapterID = 14;
                break;
            case "Half Guard Bottom":
                chapterID = 15;
                break;
            case "Half Guard Top":
                chapterID = 16;
                break;
            case "Back Mount Controls":
                chapterID = 17;
                break;
            case "Back Mount Submissions":
                chapterID = 18;
                break;
            case "Back Mount Submission Counters":
                chapterID = 19;
                break;
            case "Leg Locks Straight Foot Locks":
                chapterID = 20;
                break;
            case "Leg Locks Toe Holds":
                chapterID = 21;
                break;
            case "Leg Locks Knee Locks":
                chapterID = 22;
                break;
            case "Leg Locks Heel Hooks":
                chapterID = 23;
                break;
            case "Standing Clinch":
                chapterID = 24;
                break;
            case "Standing Takedowns":
                chapterID = 25;
                break;
            case "Standing Weapon Defences":
                chapterID = 26;
                break;
            case "Standing Front Attack Defences":
                chapterID = 27;
                break;
            case "Standing Rear Attack Defences":
                chapterID = 28;
                break;
            default:
                chapterID = 0;
        }
        
        //using the chapter ID we can now populate the list view with the relevant notes
        populateListView(chapterID);

        //Set on click listener for when a note is clicked (view that note)
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                goToViewNote(position, chapterID);
            }
        });

        ///when add button is clicked
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelper.addData();
                populateListView(chapterID);
            }
        });

    }

    private void goToViewNote(int position, Integer chapterID) {
        //function which takes user to view note screen for specific note selected
        ArrayList<String> IDs = getIDs(chapterID);
        Intent intent = new Intent(getApplicationContext(), ViewNote.class);
        intent.putExtra("NoteID", Integer.parseInt(IDs.get(position)));
        startActivity(intent);
    }

    private ArrayList<String> getIDs(Integer chapterID) {
        //function which returns the IDs of notes in this chapter
        Cursor data = databaseHelper.getData(chapterID);
        ArrayList<String> noteIDs = new ArrayList<>();
        while (data.moveToNext()){
            //get ID of note from the DB then move to next record
            noteIDs.add(data.getString(0));
        }
        return noteIDs;
    }

    private void populateListView(Integer chapterID) {
        //function which when called displays the notes that are relevant to this chapter
        Cursor data = databaseHelper.getData(chapterID);
        ArrayList<String> noteTitles = new ArrayList<>();
        while (data.moveToNext()){
            //get title of note from the DB then move to next record
            noteTitles.add(data.getString(1));
        }
        //create the adapter
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, noteTitles);
        listView.setAdapter(adapter);
    }

    public void goBack(View view) {
        finish();
    }

    public void goToAddNote(View view) {
    }
}
