package com.example.mjlife;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class Timeline extends AppCompatActivity {

    Button btnPlus;
    View dialogView;
    Spinner subjectSp, scheduleSp;
    TextView grade, start_text, end_text;
    TimePicker start_time, end_time;
    int sHour, sMin, eHour, eMin; //시간 저장 변수
    String id;
    DrawerLayout drawerLayout;
    NavigationView nDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timeline);

        Intent intent = getIntent();
        id = intent.getStringExtra("id");

        btnPlus = findViewById(R.id.btnPlus);
        grade = findViewById(R.id.grade);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        nDrawer = (NavigationView) findViewById(R.id.nDrawer);
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        naviItem();
        setToolbar();

        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder tlDlg = new AlertDialog.Builder(Timeline.this);
                tlDlg.setTitle("시간표 추가");

                dialogView = View.inflate(Timeline.this,R.layout.dialog01, null);
                tlDlg.setView(dialogView);
                tlDlg.setPositiveButton("확인", null);
                tlDlg.setNegativeButton("취소", null);
                tlDlg.show();

                //과목 스피너
                subjectSp = dialogView.findViewById(R.id.sbSpinner);
                ArrayAdapter sbAdapter = ArrayAdapter.createFromResource(Timeline.this, R.array.subject,android.R.layout.simple_spinner_dropdown_item);
                subjectSp.setAdapter(sbAdapter);

                //요일 스피너
                scheduleSp = dialogView.findViewById(R.id.scSpinner);
                ArrayAdapter scAdapter = ArrayAdapter.createFromResource(Timeline.this, R.array.day,android.R.layout.simple_spinner_dropdown_item);
                scheduleSp.setAdapter(scAdapter);

                //시간 인플레이팅
                start_text = dialogView.findViewById(R.id.start_text);
                end_text = dialogView.findViewById(R.id.end_text);
                start_time = dialogView.findViewById(R.id.start_time);
                end_time = dialogView.findViewById(R.id.end_time);

                //시작 시간 텍스트 눌렀을때
                start_text.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        start_time.setVisibility(View.VISIBLE);
                        end_time.setVisibility(View.INVISIBLE);
                    }
                });

                //시작 시간 변화 이벤트
                start_time.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
                    @Override
                    public void onTimeChanged(TimePicker timePicker, int hour, int min) {
                        sHour = hour;
                        sMin = min;
                        start_text.setText(sHour+":"+sMin);
                    }
                });

                //끝나는 시간 눌렀을때
                end_text.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        start_time.setVisibility(View.INVISIBLE);
                        end_time.setVisibility(View.VISIBLE);
                    }
                });

                //끝나는 시간 변화 이벤트
                end_time.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
                    @Override
                    public void onTimeChanged(TimePicker timePicker, int hour, int min) {
                        eHour = hour;
                        eMin = min;
                        end_text.setText(eHour+":"+eMin);
                    }
                });

            }
        });
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
