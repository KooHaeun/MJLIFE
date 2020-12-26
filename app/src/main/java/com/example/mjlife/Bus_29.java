package com.example.mjlife;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

class Bus_29 extends AppCompatActivity {//완료
    String id;
    DrawerLayout drawerLayout;
    static int temp;    //현재 페이지에 가져온 시트 번호를 공유하기 위한 변수
    DatabaseReference mDatabase;
    HashMap result;
    String where = "홍대";

    int seatID[] = {R.id.s1, R.id.s2, R.id.s3, R.id.s4, R.id.s5, R.id.s6, R.id.s7, R.id.s8, R.id.s9, R.id.s10,
            R.id.s11, R.id.s12, R.id.s13, R.id.s14, R.id.s15, R.id.s16, R.id.s17, R.id.s18, R.id.s19, R.id.s20,
            R.id.s21, R.id.s22, R.id.s23, R.id.s24, R.id.s25, R.id.s26, R.id.s27, R.id.s28, R.id.s29};

    Button seat[];

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bus_29);
        seat = new  Button[29];

        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        mDatabase = FirebaseDatabase.getInstance().getReference("Student");
        result=new HashMap();

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        setToolbar();
        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat dayFormat = new SimpleDateFormat("dd", Locale.getDefault());
        SimpleDateFormat monthFormat = new SimpleDateFormat("MM", Locale.getDefault());

        String nowm = monthFormat.format(currentTime);
        String nowd = dayFormat.format(currentTime);
        final String today = nowm+"월 "+nowd+"일";

        Intent getReservation = new Intent(this.getIntent());

        //Reservation에서 보낸 운행수 받아옴
        final String getServiceNum = getReservation.getStringExtra("selectValue");

        for (int temp = 0; temp < 29; temp++) {
            seat[temp] = findViewById(seatID[temp]);
        }
        //인텐트로 운행수 받아오고 버스 좌석 번호 받아서 변수에 저장까지만

        for (temp = 0; temp < 29; temp++) {
            seat[temp].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int i = temp;
                    String seatNum = seat[i].getText().toString();  //선택한 버튼의 좌석번호

                    result.put("date", today);
                    result.put("serviceNum", getServiceNum);
                    result.put("seatNum", seatNum);
                    result.put("where", where);
                    result.put("ID", id);
                    mDatabase.child(id).child("Reservation").child("Bus").updateChildren(result);
                    seat[i].setEnabled(false);
                    Toast.makeText(getApplicationContext(),(today+" "+where+"행 "+getServiceNum+"회차 버스가 예약되었습니다."), Toast.LENGTH_SHORT).show();
                    finish();
                }
            });
        }
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
}
