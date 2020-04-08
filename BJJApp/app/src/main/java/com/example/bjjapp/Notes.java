package com.example.bjjapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
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

        ///when add button is clicked
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelper.addData();
                populateListView();
            }
        });

        populateListView();
    }

    private void populateListView() {
        //function which when called displays the notes that are relevant to this chapter
        Cursor data = databaseHelper.getData();
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
