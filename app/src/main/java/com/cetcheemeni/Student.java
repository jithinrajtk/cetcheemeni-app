package com.cetcheemeni;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class Student extends AppCompatActivity implements View.OnClickListener {

    Button b1,b2,b3,b4,b5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        getSupportActionBar().setElevation(0);


        b1 = (Button) findViewById(R.id.b1);
        b2 = (Button) findViewById(R.id.b2);
        b3 = (Button) findViewById(R.id.b3);
        b4 = (Button) findViewById(R.id.b4);
        b5 = (Button) findViewById(R.id.b5);


        b1.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/bell.ttf"));
        b2.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/bell.ttf"));
        b3.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/bell.ttf"));
        b4.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/bell.ttf"));
        b5.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/bell.ttf"));


        try {
            b1.setOnClickListener(this);
            b2.setOnClickListener(this);
            b3.setOnClickListener(this);
            b4.setOnClickListener(this);
            b5.setOnClickListener(this);

        } catch (Exception e) {

        }
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.b1:
                startActivity(new Intent(getApplicationContext(), cse.class));
                break;
            case R.id.b2:
                startActivity(new Intent(getApplicationContext(), ece.class));
                break;
            case R.id.b3:
                startActivity(new Intent(getApplicationContext(), eee.class));
                break;
            case R.id.b4:
                startActivity(new Intent(getApplicationContext(), ce.class));
                break;
            case R.id.b5:
                startActivity(new Intent(getApplicationContext(), ite.class));
                break;


        }
    }



}
