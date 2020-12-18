package com.example.mjlife;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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

                if(telGet.equals("") || emailGet.equals("") || passGet.equals("") || passCheckGet.equals("")){
                    Toast.makeText(Account_info.this, "모든 내용을 입력해주세요!", Toast.LENGTH_SHORT).show();
                }else{
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
                }
            }
        });
    }
}
