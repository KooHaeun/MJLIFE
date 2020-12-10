package com.example.mjlife;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Bus_45 extends AppCompatActivity {
    int seatID[] = {R.id.s1, R.id.s2, R.id.s3, R.id.s4, R.id.s5, R.id.s6, R.id.s7, R.id.s8, R.id.s9, R.id.s10,
            R.id.s11, R.id.s12, R.id.s13, R.id.s14, R.id.s15, R.id.s16, R.id.s17, R.id.s18, R.id.s19, R.id.s20,
            R.id.s21, R.id.s22, R.id.s23, R.id.s24, R.id.s25, R.id.s26, R.id.s27, R.id.s28, R.id.s29, R.id.s30,
            R.id.s31, R.id.s32, R.id.s33, R.id.s34, R.id.s35, R.id.s36, R.id.s37, R.id.s38, R.id.s39, R.id.s40,
            R.id.s41, R.id.s42, R.id.s43, R.id.s44, R.id.s45};

    Button seat[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bus_45);

        Intent getReservation = new Intent(this.getIntent());

        //Reservation에서 보낸 운행수 받아옴
        String getServiceNum =  getReservation.getStringExtra("selectValue");

        for(int i = 0; i<45; i++){
            seat[i] = findViewById(seatID[i]);
        }
    }
}
