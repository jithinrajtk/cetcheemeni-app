package com.cetcheemeni;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.URL;

public class College extends AppCompatActivity {

    ImageView newimg;
    TextView b1;
    Bitmap bitmap;
    ProgressDialog pDialog;

    protected Bitmap doInBackground(String... args) {
        try {
            College.this.bitmap = BitmapFactory.decodeStream((InputStream) new URL(args[0]).getContent());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return College.this.bitmap;
    }

    protected void onPostExecute(Bitmap image) {
        if (image != null) {
            College.this.newimg.setImageBitmap(image);
            College.this.pDialog.dismiss();
            return;
        }
        College.this.pDialog.dismiss();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college);

        this.newimg = (ImageView) findViewById(R.id.newimg);
        newimg.setImageResource(R.drawable.bus);

        getSupportActionBar().setElevation(0);

    }
}
