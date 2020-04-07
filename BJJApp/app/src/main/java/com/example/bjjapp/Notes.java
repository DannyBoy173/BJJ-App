package com.example.bjjapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;

public class Notes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        Intent intent = getIntent();
        String chapter = intent.getStringExtra("Chapter");
        String subchapter = intent.getStringExtra("SubChapter");

        TextView title = (TextView)findViewById(R.id.notes_title);
        title.setText(chapter + "\n" + subchapter);

//        final ArrayList<String> datasource; //list storing notes for this chapter (retrieved from DB)
//
//        final ArrayAdapter<String> adapter = new ArrayAdapter<String> (getApplicationContext(),R.layout.alarm_list_item,
//                R.id.alarm_list_item_text,datasource);
//        ListView lv = (ListView) findViewById(R.id.alarmListView);
//        lv.setAdapter(adapter);
//
//        datasource.add(dayAsStr + " - " + hourStr + ":" + minStr);
//        adapter.notifyDataSetChanged();
    }

    public void goBack(View view) {
        finish();
    }

    public void goToAddNote(View view) {
    }
}
