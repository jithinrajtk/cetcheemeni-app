package com.cetcheemeni;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class PrinciMain extends AppCompatActivity implements View.OnClickListener {

    Button head,b1;
    ImageView newimg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_princi_main);

        getSupportActionBar().setElevation(0);

        b1 = (Button) findViewById(R.id.b1);


        this.newimg = (ImageView) findViewById(R.id.newimg);
        newimg.setImageResource(R.drawable.bijuprinci);

        try {
            b1.setOnClickListener(this);

        } catch (Exception e) {

        }
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.b1:
                startActivity(new Intent(getApplicationContext(), Principal.class));
                break;

        }


    }
}
