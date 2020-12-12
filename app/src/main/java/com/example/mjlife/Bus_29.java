package com.example.mjlife;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Bus_29 extends AppCompatActivity {
    static int temp;    //현재 페이지에 가져온 시트 번호를 공유하기 위한 변수

    int seatID[] = {R.id.s1, R.id.s2, R.id.s3, R.id.s4, R.id.s5, R.id.s6, R.id.s7, R.id.s8, R.id.s9, R.id.s10,
            R.id.s11, R.id.s12, R.id.s13, R.id.s14, R.id.s15, R.id.s16, R.id.s17, R.id.s18, R.id.s19, R.id.s20,
            R.id.s21, R.id.s22, R.id.s23, R.id.s24, R.id.s25, R.id.s26, R.id.s27, R.id.s28, R.id.s29};

    Button seat[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bus_29);

        Intent getReservation = new Intent(this.getIntent());

        //Reservation에서 보낸 운행수 받아옴
        final String getServiceNum = getReservation.getStringExtra("selectValue");

        for (int temp = 0; temp < 29; temp++) {
            seat[temp] = findViewById(seatID[temp]);
        }
        //인텐트로 운행수 받아오고 버스 좌석 번호 받아서 변수에 저장까지만

        for (temp = 0; temp < 29; temp++) {
            seat[temp].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int i = temp;
                    String seatNum = seat[i].getText().toString();  //선택한 버튼의 좌석번호

                }
            });
        }
    }
}
