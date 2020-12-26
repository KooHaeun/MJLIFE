package com.example.mjlife;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    Button homepageButton;

    DrawerLayout drawerLayout;
    NavigationView nDrawer;

    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //홈페이지 버튼 인플레이션
        homepageButton = findViewById(R.id.homepageButton);

        //홈페이지 버튼 누르면 homepage_webview 액티비티로 이동
        homepageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toHomePage = new Intent(MainActivity.this, Homepage_WebView.class);
                startActivity(toHomePage);
            }
        });

        //로그인 화면에서 id 받아오기
        Intent intent = getIntent();
        id = intent.getStringExtra("id");

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        nDrawer = (NavigationView) findViewById(R.id.nDrawer);
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        naviItem();
        setToolbar();
    }

    public void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.bar);
        ImageButton menu = findViewById(R.id.menu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.END);
            }
        });
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(false);
        TextView title = findViewById(R.id.title);
        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //메인 화면으로 이동
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void naviItem(){
        nDrawer.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                menuItem.setChecked(true);
                drawerLayout.closeDrawers();

                int id = menuItem.getItemId();
                Intent intent;

                if(id==R.id.reservation){
                    //reservation으로
                    //id넘기기
                    intent = new Intent(getApplicationContext(), Reservation.class);
                    intent.putExtra("id", id);
                    startActivity(intent);
                }
                else if(id==R.id.timeline){
                    //timeline으로
                    //id넘기기
                    intent = new Intent(getApplicationContext(), Timeline.class);
                    intent.putExtra("id", id);
                    startActivity(intent);
                }
                else if(id == R.id.mine){
                    //Account_info로 넘기기
                    //id 넘기기
                    intent = new Intent(getApplicationContext(), Account_info.class);
                    intent.putExtra("id", id);
                    startActivity(intent);
                }else if(id==R.id.reservation_con){
                    //ReservationContrl로 넘기기
                    //id 넘기기
                    intent = new Intent(getApplicationContext(), ReservationControl.class);
                    intent.putExtra("id", id);
                    startActivity(intent);
                }
                return true;
            }
        });
    }
}
