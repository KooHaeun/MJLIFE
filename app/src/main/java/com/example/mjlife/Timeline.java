package com.example.mjlife;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Timeline extends AppCompatActivity {

    Button btnPlus;
    View dialogView;
    Spinner tlSpinner;
    TextView grade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPlus = findViewById(R.id.btnPlus);
        grade = findViewById(R.id.grade);

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

                tlSpinner = dialogView.findViewById(R.id.tlSpinner);
                ArrayAdapter tlAdapter = ArrayAdapter.createFromResource(Timeline.this, R.array.subject,android.R.layout.simple_spinner_item);
                tlSpinner.setAdapter(tlAdapter);
            }
        });
    }
}
