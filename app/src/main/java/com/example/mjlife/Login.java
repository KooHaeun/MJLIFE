package com.example.mjlife;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    RadioGroup rdg;
    RadioButton rdS, rdP;
    Button login, join;
    DatabaseReference sDatabase, pDatabase;
    String id, pw;
    EditText ID, password;

    protected void onCreate(Bundle savedInstanceState) {//완료
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        rdg = findViewById(R.id.rdg);
        rdP = findViewById(R.id.rdoP);
        rdS = findViewById(R.id.rdoS);
        login = findViewById(R.id.login);
        join = findViewById(R.id.join);
        ID = findViewById(R.id.ID);
        password = findViewById(R.id.password);

        sDatabase = FirebaseDatabase.getInstance().getReference("Student");
        pDatabase = FirebaseDatabase.getInstance().getReference("Professor");



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                id = ID.getText().toString();
                pw = password.getText().toString();

                if (id == null || pw == null) {
                    Toast.makeText(getApplicationContext(), "아이디 또는 비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show();
                } else {
                    switch (rdg.getCheckedRadioButtonId()) {
                        case R.id.rdoS:
                            sDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    for (DataSnapshot student : snapshot.getChildren()) {
                                        if (student.child("id").getValue().toString().equals(id)&&student.child("password").getValue().toString().equals(pw)) {
                                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                            intent.putExtra("id",student.getKey());
                                            startActivity(intent);
                                            finish();
                                            return;
                                        }
                                    }
                                    Toast.makeText(getApplicationContext(), "아이디 또는 비밀번호를 확인해주세요", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                            break;
                        case R.id.rdoP:
                            pDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    for (DataSnapshot professor : snapshot.getChildren()) {
                                        if (professor.child("id").getValue().toString().equals(id)&&professor.child("password").getValue().toString().equals(pw)) {
                                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                            intent.putExtra("id", professor.getKey());
                                            startActivity(intent);
                                            finish();
                                            return;
                                        }
                                    }
                                    Toast.makeText(getApplicationContext(), "아이디 또는 비밀번호를 확인해주세요", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                            break;
                        default:
                            Toast.makeText(getApplicationContext(), "회원 종류를 선택해주세요", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }

}
