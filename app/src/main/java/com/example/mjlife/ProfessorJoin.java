package com.example.mjlife;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;

import java.util.HashMap;

public class ProfessorJoin extends AppCompatActivity {

    private final int GALLERY_CODE = 10;
    EditText name_in, id_in, password_in, phone_in, passCheck_in;
    Button btnUpload, check, complete;
    DatabaseReference mDatabase;
    HashMap result;
    int c=0, k=0;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.professor_join);
    }
}
