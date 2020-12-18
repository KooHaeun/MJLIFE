package com.example.mjlife;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Pattern;

public class Account_info extends AppCompatActivity {

    EditText telInput, emailInput, passInput, passCheckInput;
    String telGet, emailGet, passGet, passCheckGet;
    Button completeButton, logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_info);

        telInput = findViewById(R.id.telInput);
        emailInput = findViewById(R.id.emailInput);
        passInput = findViewById(R.id.passInput);
        passCheckInput = findViewById(R.id.passCheckInput);

        completeButton = findViewById(R.id.completeButton);
        logoutButton = findViewById(R.id.logoutButton);

        completeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                telGet = telInput.getText().toString();
                emailGet = emailInput.getText().toString();
                passGet = passInput.getText().toString();
                passCheckGet = passCheckInput.getText().toString();

                if (telGet.equals("") || emailGet.equals("") || passGet.equals("") || passCheckGet.equals("")) {   // 빈칸이 있는지 검사
                    Toast.makeText(Account_info.this, "모든 내용을 입력해주세요!", Toast.LENGTH_SHORT).show();

                } else { // 빈칸이 없으면
                    if (!android.util.Patterns.EMAIL_ADDRESS.matcher(emailGet).matches()){ // 이메일 형식 검사
                        Toast.makeText(Account_info.this, "이메일 형식에 맞게 입력해주세요.", Toast.LENGTH_SHORT).show();

                    } else if(!Pattern.matches("^[0-9].{8,11}$", telGet)){ // 전화번호 숫자, 형식 검사
                        Toast.makeText(Account_info.this, "전화번호를 숫자로만 입력해주세요.", Toast.LENGTH_SHORT).show();

                    } else if(!Pattern.matches("^[a-zA-Z0-9].{8,16}$", telGet)){ // 비밀번호 검사
                        Toast.makeText(Account_info.this, "비밀번호는 숫자와 영문자를 합쳐서 8~16자로 입력하세요.", Toast.LENGTH_SHORT).show();

                    } else { // 빈칸이 없고 아무 문제도 없으면
                        if(passGet.equals(passCheckGet)) { // 비밀번호와 비밀번호확인의 값이 같은지 검사해서 같을 경우
                            AlertDialog.Builder dlg = new AlertDialog.Builder(Account_info.this);
                            dlg.setTitle("수정완료");
                            dlg.setMessage("수정이 완료되었습니다.");
                            dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    ;
                                }
                            });
                            dlg.show();
                        }else{ // 두 개의 비밀번호 값이 틀린 경우
                            Toast.makeText(Account_info.this, "비밀번호를 동일하게 입력했는지 확인하세요.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
    }
}
