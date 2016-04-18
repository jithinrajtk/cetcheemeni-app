package com.cetcheemeni;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

public class About extends AppCompatActivity {

    Button head;
    ImageView newimg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        this.newimg = (ImageView) findViewById(R.id.newimg);
        newimg.setImageResource(R.drawable.mainpic);

        head = (Button) findViewById(R.id.head);
        head.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/notti.ttf"));

    }
}
