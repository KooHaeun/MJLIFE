package com.example.mjlife;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class Account_info extends AppCompatActivity {

    EditText nameInput, emailInput, passInput, passCheckInput;
    String nameGet, emailGet, passGet, passCheckGet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_info);

        nameInput = findViewById(R.id.nameInput);
        emailInput = findViewById(R.id.emailInput);
        passInput = findViewById(R.id.passInput);
        passCheckInput = findViewById(R.id.passCheckInput);

        nameGet = nameInput.getText().toString();
        emailGet = emailInput.getText().toString();
        passGet = passInput.getText().toString();
        passCheckGet = passCheckInput.getText().toString();
    }
}
