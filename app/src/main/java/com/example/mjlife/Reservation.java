package com.example.mjlife;

import android.content.Intent;
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

        btnCoun = findViewById(R.id.btnCoun);

        one45 = findViewById(R.id.one45);
        two45 = findViewById(R.id.two45);
        three45 = findViewById(R.id.three45);
        four45 = findViewById(R.id.four45);
        five45 = findViewById(R.id.five45);
        six45 = findViewById(R.id.six45);

        one29 = findViewById(R.id.one29);
        two29 = findViewById(R.id.two29);
        three29 = findViewById(R.id.three29);
        four29 = findViewById(R.id.four29);
        five29 = findViewById(R.id.five29);
        six29 = findViewById(R.id.six29);
        //초기화 (완료했습니다.)

        btnCoun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Counsel로 인텐트 전환
                Intent toCounsel = new Intent(Reservation.this, Counsel.class);
                startActivity(toCounsel);
            }
        });


    }

    //textview에다 onClick이벤트 줘서 29는 Bus_29로 45는 Bus_45로 넘기기
    public void Click49(View v) {
        Intent toBus_45 = new Intent(Reservation.this, Bus_45.class);

        //선택한 운행수에 따라서 해당 번호를 같이 보냄, 보낸 번호로 운행수 확인
        if(v == one45) toBus_45.putExtra("selectValue", String.valueOf(one45.getText()));
        else if(v == two45) toBus_45.putExtra("selectValue", String.valueOf(two45.getText()));
        else if(v == three45) toBus_45.putExtra("selectValue", String.valueOf(three45.getText()));
        else  if(v == four45) toBus_45.putExtra("selectValue", String.valueOf(four45.getText()));
        else  if(v == five45) toBus_45.putExtra("selectValue", String.valueOf(five45.getText()));
        else toBus_45.putExtra("selectValue", String.valueOf(six45.getText()));

        startActivity(toBus_45);
    }

    public void Click29(View v) {
        Intent toBus_29 = new Intent(Reservation.this, Bus_29.class);

        //선택한 운행수에 따라서 해당 번호를 같이 보냄, 보낸 번호로 운행수 확인
        if(v == one45) toBus_29.putExtra("selectValue", String.valueOf(one29.getText()));
        else if(v == two45) toBus_29.putExtra("selectValue", String.valueOf(two29.getText()));
        else if(v == three45) toBus_29.putExtra("selectValue", String.valueOf(three29.getText()));
        else  if(v == four45) toBus_29.putExtra("selectValue", String.valueOf(four29.getText()));
        else  if(v == five45) toBus_29.putExtra("selectValue", String.valueOf(five29.getText()));
        else toBus_29.putExtra("selectValue", String.valueOf(six29.getText()));

        startActivity(toBus_29);
    }

}
