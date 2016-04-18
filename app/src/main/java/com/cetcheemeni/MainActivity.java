package com.cetcheemeni;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.pushbots.push.Pushbots;



import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b13,b12,head,head1,headml;
    TextView t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12;
    Bitmap bitmap;
    int x=0;
    ImageView newimg;
    ProgressDialog pDialog;
    Button req_ad;
    Button skip;
    String text;
    Thread thread;

    /*db*/
    Activity context;
    ProgressDialog pd;




    /**/

    private class LoadImage extends AsyncTask<String, String, Bitmap> {
        private LoadImage() {
        }

        protected void onPreExecute() {
            super.onPreExecute();
            MainActivity.this.pDialog = new ProgressDialog(MainActivity.this);
           // MainActivity.this.pDialog.setTitle("Image Loading");
          //  MainActivity.this.pDialog.setProgressPercentFormat();
           // MainActivity.this.pDialog.setMessage("checking connection...");
            MainActivity.this.pDialog.setCancelable(false);
           // MainActivity.this.pDialog.show();
        }

        protected Bitmap doInBackground(String... args) {
            try {
                MainActivity.this.bitmap = BitmapFactory.decodeStream((InputStream) new URL(args[0]).getContent());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return MainActivity.this.bitmap;
        }

        protected void onPostExecute(Bitmap image) {
            if (image != null) {
                MainActivity.this.newimg.setImageBitmap(image);
                MainActivity.this.pDialog.dismiss();
                return;
            }
            MainActivity.this.pDialog.dismiss();
        }
    }


    /**/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //this.text = "http://cetkr.in/wp-content/uploads/2014/09/s21-1030x258.jpg";
        this.newimg = (ImageView) findViewById(R.id.newimg);

        newimg.setImageResource(R.drawable.mainpic);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                newimg.setImageResource(R.drawable.mainpic2);
            }
        }, 3000);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                newimg.setImageResource(R.drawable.mainpic3);
            }
        }, 6000);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                newimg.setImageResource(R.drawable.mainpic4);
            }
        }, 9000);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                newimg.setImageResource(R.drawable.mainpic5);
            }
        }, 12000);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                newimg.setImageResource(R.drawable.mainpic6);
            }
        }, 12000);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                newimg.setImageResource(R.drawable.mainpic7);
            }
        }, 12000);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                newimg.setImageResource(R.drawable.lasthead);
            }
        }, 15000);

       // new LoadImage().execute(new String[]{this.text});


        context = this;

        Pushbots.sharedInstance().init(this);

        b1 = (Button) findViewById(R.id.b1);
        b2 = (Button) findViewById(R.id.b2);
        b3 = (Button) findViewById(R.id.b3);
        b4 = (Button) findViewById(R.id.b4);
        b5 = (Button) findViewById(R.id.b5);
        b6 = (Button) findViewById(R.id.b6);
        b7 = (Button) findViewById(R.id.b7);
        b8 = (Button) findViewById(R.id.b8);
        b9 = (Button) findViewById(R.id.b9);
        b10 = (Button) findViewById(R.id.b10);
        b13 = (Button) findViewById(R.id.b13);
        b12 = (Button) findViewById(R.id.b12);

        // mar = (Button) findViewById(R.id.mar);


        /*b10 = (Button) findViewById(R.id.b10);
        b10.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/notti.ttf"));*/





        t1 = (TextView) findViewById(R.id.t1);
        t1.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/notti.ttf"));
        t2 = (TextView) findViewById(R.id.t2);
        t2.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/notti.ttf"));
        t3 = (TextView) findViewById(R.id.t3);
        t3.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/notti.ttf"));
        t4 = (TextView) findViewById(R.id.t4);
        t4.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/notti.ttf"));
        t5 = (TextView) findViewById(R.id.t5);
        t5.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/notti.ttf"));
        t6 = (TextView) findViewById(R.id.t6);
        t6.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/notti.ttf"));
        t7 = (TextView) findViewById(R.id.t7);
        t7.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/notti.ttf"));
        t8 = (TextView) findViewById(R.id.t8);
        t8.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/notti.ttf"));
        t9 = (TextView) findViewById(R.id.t9);
        t9.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/notti.ttf"));

        t10 = (TextView) findViewById(R.id.t10);
        t10.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/notti.ttf"));
        t11 = (TextView) findViewById(R.id.t11);
        t11.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/notti.ttf"));
        t12 = (TextView) findViewById(R.id.t12);
        t12.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/notti.ttf"));





        head = (Button) findViewById(R.id.head);
        head.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/notti.ttf"));

        headml = (Button) findViewById(R.id.head);
        headml.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/rach.ttf"));

        head1 = (Button) findViewById(R.id.head);
        head1.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/notti.ttf"));

        b1.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/fontawesome.ttf"));
        b1.setText("\uf0a1");

        b2.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/fontawesome.ttf"));
        b2.setText("\uf19d");

        b3.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/fontawesome.ttf"));
        b3.setText("\uf073");

        b4.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/fontawesome.ttf"));
        b4.setText("\uf256");

        b5.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/fontawesome.ttf"));
        b5.setText("\uf288");

        b6.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/fontawesome.ttf"));
        b6.setText("\uf1ad");

        b7.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/fontawesome.ttf"));
        b7.setText("\uf0c0");

        b8.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/fontawesome.ttf"));
        b8.setText("\uf0ac");

        b9.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/fontawesome.ttf"));
        b9.setText("\uf207");

        b10.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/fontawesome.ttf"));
        b10.setText("\uf030");

        b13.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/fontawesome.ttf"));
        b13.setText("\uf003");

        b12.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/fontawesome.ttf"));
        b12.setText("\uf129");
        

        try {
            b1.setOnClickListener(this);
            b2.setOnClickListener(this);
            b3.setOnClickListener(this);
            b4.setOnClickListener(this);
            b5.setOnClickListener(this);
            b6.setOnClickListener(this);
            b7.setOnClickListener(this);
            b8.setOnClickListener(this);
            b9.setOnClickListener(this);
            b10.setOnClickListener(this);
            b13.setOnClickListener(this);
            b12.setOnClickListener(this);

        } catch (Exception e) {

        }
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.b1:
                startActivity(new Intent(getApplicationContext(), Notify.class));
                break;
            case R.id.b2:
                startActivity(new Intent(getApplicationContext(), Student.class));
                break;
            case R.id.b3:
                startActivity(new Intent(getApplicationContext(), Events.class));
                break;
            case R.id.b4:
                startActivity(new Intent(getApplicationContext(), Facilities.class));
                break;
            case R.id.b5:
                startActivity(new Intent(getApplicationContext(), PrinciMain.class));
                break;
            case R.id.b6:
                startActivity(new Intent(getApplicationContext(), Dept.class));
                break;
            case R.id.b7:
                startActivity(new Intent(getApplicationContext(), Fac.class));
                break;
            case R.id.b8:
                startActivity(new Intent(getApplicationContext(), Commu.class));
                break;
            case R.id.b9:
                startActivity(new Intent(getApplicationContext(), College.class));
                break;
            case R.id.b10:
                startActivity(new Intent(getApplicationContext(), PhotoGallery.class));
                break;
            case R.id.b13:
                startActivity(new Intent(getApplicationContext(), Messagebox.class));
                break;
            case R.id.b12:
                startActivity(new Intent(getApplicationContext(), About.class));
                break;


        }
    }
    @Override
    public void onBackPressed(){
        if(x==0) {
            Toast.makeText(MainActivity.this, "Press again to exit", Toast.LENGTH_SHORT).show();
            x++;
        }
        else
        {
            finish();
        }
    }

}
