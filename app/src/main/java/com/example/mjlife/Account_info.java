package com.example.mjlife;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.regex.Pattern;

public class Account_info extends AppCompatActivity {

    EditText telInput, emailInput, passInput, passCheckInput;
    String telGet, emailGet, passGet, passCheckGet;
    Button completeButton, logoutButton;
    String id;
    DrawerLayout drawerLayout;
    NavigationView nDrawer;
    Integer num = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {//완료
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_info);

        telInput = findViewById(R.id.telInput);
        passInput = findViewById(R.id.passInput);
        passCheckInput = findViewById(R.id.passCheckInput);

        completeButton = findViewById(R.id.completeButton);
        logoutButton = findViewById(R.id.logoutButton);

        Intent intent = getIntent();
        id = intent.getStringExtra("id");

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        nDrawer = (NavigationView) findViewById(R.id.nDrawer);
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        naviItem();
        setToolbar();
        final DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference();

        completeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final HashMap result = new HashMap<>();
                telGet = telInput.getText().toString();
                passGet = passInput.getText().toString();
                passCheckGet = passCheckInput.getText().toString();

                if(!telGet.equals("")){
                    if (!Pattern.matches("^[0-9].{8,11}$", telGet)){ // 전화번호 숫자, 형식 검사
                        Toast.makeText(Account_info.this, "전화번호를 숫자로만 입력해주세요.", Toast.LENGTH_SHORT).show();
                        num++;
                     }
                    else{
                        result.put("phone", telGet);
                        num--;
                    }
                }

                if(!passGet.equals("")){
                    if (!passGet.equals(passCheckGet)) { // 비밀번호와 비밀번호확인의 값이 같은지 검사해서 같을 경우
                        Toast.makeText(Account_info.this, "비밀번호를 동일하게 입력했는지 확인하세요.", Toast.LENGTH_SHORT).show();
                        num++;
                    }
                    else {
                        result.put("password", passGet);
                        num--;
                    }
                }
                if(num<=0) {
                    AlertDialog.Builder dlg = new AlertDialog.Builder(Account_info.this);
                    dlg.setTitle("수정완료");
                    dlg.setMessage("수정이 완료되었습니다.");
                    dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            mDatabase.child("Student").child(id).updateChildren(result);
                        }
                    });
                    dlg.show();
                }
             }
         });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finishAffinity();
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
