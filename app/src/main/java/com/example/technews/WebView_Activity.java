package com.example.technews;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class WebView_Activity extends AppCompatActivity {

    WebView webView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        webView = findViewById(R.id.webid);

        Intent intent = getIntent();
        String URL = intent.getStringExtra("url");

        try{
            webView.loadUrl(URL);
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(),"Issues in loading",Toast.LENGTH_SHORT).show();
        }


    }
}