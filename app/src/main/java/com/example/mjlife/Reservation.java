package com.example.mjlife;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Reservation extends AppCompatActivity {

    Button btnCoun, btnBus;
    TextView one45, two45, three45, four45, five45, six45, one29, two29, three29, four29, five29, six29;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reservation);

        btnCoun=findViewById(R.id.btnCoun);
        one29 = findViewById(R.id.one29);
        two29 = findViewById(R.id.two29);
        three29=findViewById(R.id.three29);
        //초기화

        btnCoun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Counsel로 인텐트 전환
            }
        });

        //textview에다 onClick이벤트 줘서 29는 Bus_29로 45는 Bus_45로 넘기기
    }


}
