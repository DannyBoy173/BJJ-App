package com.example.bjjapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class ViewNote extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    private ImageButton btnDelete;
    private ImageButton btnEdit;
    private ImageButton btnShare;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_note);
        databaseHelper = new DatabaseHelper(this);
        String noteTitle = new String();
        String notes = new String();
        String links = new String();
        btnDelete = (ImageButton) findViewById(R.id.deleteBtn);
        btnEdit = (ImageButton) findViewById(R.id.editBtn);
        btnShare = (ImageButton) findViewById(R.id.shareBtn);
        listView = (ListView) findViewById(R.id.linksListView);

        //get the intent data to display the correct note (the note ID)
        Intent intent = getIntent();
        final Integer ID = intent.getIntExtra("NoteID",0);

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

        //create the links list view
        String[] linkArray = links.split("\\s+");
        final ArrayList<String> linkList = new ArrayList<>();
        Collections.addAll(linkList, linkArray);
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, linkList);
        listView.setAdapter(adapter);

        //Set on click listener for when a link is clicked (implicit intent)
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String clickedLink = linkList.get(position);
                Intent linkIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(clickedLink));
                try{
                    startActivity(linkIntent);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(getApplicationContext(), "Activity Not Found, try adding https:// to your link", Toast.LENGTH_LONG).show();
                }

            }
        });

        ///when delete button is clicked
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete(ID);
            }
        });

        ///when edit button is clicked
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), EditNote.class);
                intent.putExtra("NoteID", ID);
                startActivity(intent);
            }
        });

        final String finalNoteTitle = noteTitle;
        final String finalNotes = notes;
        final String finalLinks = links;
        ///when edit button is clicked
        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //combining the note into one text
                String shareText = "Title:\n" + finalNoteTitle + "\nNotes:\n" + finalNotes + "\nLinks:\n" + finalLinks;

                //share intent
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, shareText);
                sendIntent.setType("text/plain");

                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);
            }
        });

    }

    public void goBack(View view) {
        finish();
    }

    public void delete(Integer ID) {
        //delete the note being viewed
        databaseHelper.deleteNote(ID);
        finish();
    }
}
