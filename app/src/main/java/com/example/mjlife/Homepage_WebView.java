package com.example.mjlife;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

public class Homepage_WebView extends AppCompatActivity {
    WebView webView;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage_webview);

        webView = findViewById(R.id.webView);
        url = "https://www.mjc.ac.kr/mjcIndex.do";

        webView.loadUrl(url);
    }
}
