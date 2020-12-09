package com.example.mjlife;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class ProfessorJoin extends AppCompatActivity {

    private final int GALLERY_CODE = 10;
    EditText name_in, id_in, password_in, phone_in, passCheck_in;
    Spinner major_in;
    Button check, complete;
    DatabaseReference mDatabase;
    HashMap result;
    int c=0, k=0;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.professor_join);

        name_in = findViewById(R.id.name_in);
        id_in = findViewById(R.id.id_in);
        password_in = findViewById(R.id.password_in);
        passCheck_in = findViewById(R.id.passwordCheck_in);
        phone_in = findViewById(R.id.phone_in);
        check = findViewById(R.id.check);
        complete = findViewById(R.id.complete);
        mDatabase = FirebaseDatabase.getInstance().getReference("Professor");
        
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String id = id_in.getText().toString();
                k++;
                mDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot generalSnapshot : snapshot.getChildren()) {
                            if (generalSnapshot.child("id").getValue().toString().equals(id)) {
                                //중복입니다. 교번을 다시 확인해주세요 다이얼로그
                                c++;
                                k=0;
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (k == 0 || c != 0) {
                    //교번 중복확인을 해주세요 다이얼로그
                }
                else {
                    final String name = name_in.getText().toString();
                    final String id = id_in.getText().toString();
                    final String phone = phone_in.getText().toString();
                    final String major = major_in.getSelectedItem().toString();
                    if (password_in.getText().toString() == passCheck_in.getText().toString()) {
                        final String password = password_in.getText().toString();
                        if (name.equals("") || id.equals("") || phone.equals("") || password.equals("")||major.equals("")) {
                            //모든 항목을 채워주세요 다이얼로그
                        } else {
                            dbJoin(name, id, phone, password, major);
                            //가입완료 다이얼로그, 로그인 화면으로 전환
                        }
                    } else {
                        //비밀번호를 다시 확인해주세요 다이얼로그
                    }
                }
            }
        });


    }

    public void dbJoin(String name, String id, String phone, String password, String major){
        result = new HashMap();
        result.put("name", name);
        result.put("id", id);
        result.put("phone", phone);
        result.put("password", password);
        result.put("major", major);
        mDatabase.child((result.get("id").toString())).setValue(result);
    }
}
