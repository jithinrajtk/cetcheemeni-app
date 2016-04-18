package com.cetcheemeni;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;

public class PhotoGallery extends AppCompatActivity {

    ImageView newimg;
    Bitmap bitmap;
    ProgressDialog pDialog;

    protected Bitmap doInBackground(String... args) {
        try {
            PhotoGallery.this.bitmap = BitmapFactory.decodeStream((InputStream) new URL(args[0]).getContent());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return PhotoGallery.this.bitmap;
    }

    protected void onPostExecute(Bitmap image) {
        if (image != null) {
            PhotoGallery.this.newimg.setImageBitmap(image);
            PhotoGallery.this.pDialog.dismiss();
            return;
        }
        PhotoGallery.this.pDialog.dismiss();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_gallery);

        this.newimg = (ImageView) findViewById(R.id.newimg);
        newimg.setImageResource(R.drawable.photopic);

        getSupportActionBar().setElevation(0);

    }
}
