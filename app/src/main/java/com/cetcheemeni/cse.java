package com.cetcheemeni;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class cse extends AppCompatActivity implements View.OnClickListener {

    Button b1,b2,b3,b4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cse);

        b1 = (Button) findViewById(R.id.b1);
        b2 = (Button) findViewById(R.id.b2);
        b3 = (Button) findViewById(R.id.b3);
        b4 = (Button) findViewById(R.id.b4);

        try {
            b1.setOnClickListener(this);
            b2.setOnClickListener(this);
            b3.setOnClickListener(this);
            b4.setOnClickListener(this);

        } catch (Exception e) {

        }
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.b1:
                startActivity(new Intent(getApplicationContext(), FirstYearCS.class));
                break;
            case R.id.b2:
                startActivity(new Intent(getApplicationContext(), SecondYearCS.class));
                break;
            case R.id.b3:
                startActivity(new Intent(getApplicationContext(), ThirdYearCS.class));
                break;
            case R.id.b4:
                startActivity(new Intent(getApplicationContext(), FourthYearCS.class));
                break;

        }
    }

}
