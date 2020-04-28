package com.example.bjjapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

public class UserGuide extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_guide);

        WebView web = (WebView) findViewById(R.id.UserGuideWebView);
        web.loadUrl("file:///android_asset/user_guide.html");
        web.getSettings().setJavaScriptEnabled(true);
        web.requestFocus(View.FOCUS_DOWN);
    }

    public void goToMain(View view) {
        finish();
    }
}
