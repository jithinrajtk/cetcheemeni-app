package com.cetcheemeni;

import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class FirstYearEC extends AppCompatActivity {

    Activity context;
    ProgressDialog pd;
    String name;
    String rollno;
    String dob;
    String par;
    String email;
    String blank;
    String phone;
    String place;

    Button Main;

    JSONArray jArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_year_ec);
        context=this;
        name="";

        getSupportActionBar().setElevation(0);


    }
    public void onStart(){
        super.onStart();
        BackTask bt=new BackTask();
        bt.execute("http://db-cetkr.rhcloud.com/studec1.php");
    }

    public int dpToPx(int dp) {
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        int px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }

    public Activity getContext() {
        return context;
    }

    private class BackTask extends AsyncTask<String,Integer,Void> {
        String text="";
        protected void onPreExecute(){
            super.onPreExecute();
            pd = new ProgressDialog(context);
            pd.setMessage("Please wait.");
            pd.setCancelable(false);
            pd.setIndeterminate(false);
            pd.show();
        }

        protected Void doInBackground(String...params){
            URL url;
            try {
                url = new URL(params[0]);
                HttpURLConnection con=(HttpURLConnection)url.openConnection();
                InputStream is=con.getInputStream();
                BufferedReader br=new BufferedReader(new InputStreamReader(is));
                String line;
                while((line=br.readLine())!=null){
                    text+=line;

                }

                br.close();

            }catch (Exception e) {
                e.printStackTrace();
                if(pd!=null) pd.dismiss();
            }
            return null;
        }

        protected void onPostExecute(Void result) {
            if (pd != null)
                pd.dismiss();

            try {
                jArray = new JSONArray(text);

                GradientDrawable gd = new GradientDrawable();
                gd.setCornerRadius(5);
                gd.setStroke(1, 0xFF000000);

                LinearLayout layout = (LinearLayout) findViewById(R.id.noti);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    layout.setBackground(gd);
                }

                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,  LinearLayout.LayoutParams.WRAP_CONTENT);
                lp.setMargins(0, 0, 0, dpToPx(0));


                int i;
                for(i=0;i<jArray.length(); i++) {

                    JSONObject json = jArray.getJSONObject(i);
                    rollno = "Roll No : "+json.getString("rollno");
                    name = "Name : "+json.getString("name");
                    dob = "Date of Birth : "+json.getString("dob");
                    par = "Parent Name : "+json.getString("par");
                    email = "Email : "+json.getString("email");
                    phone = "Phone : "+json.getString("phone");
                    place = "Place : "+json.getString("place");

                    Main = new Button(FirstYearEC.this);
                    Main.setGravity(Gravity.LEFT);
                    Main.setBackgroundResource(R.drawable.box);
                    Main.setText(rollno.toUpperCase() + "\n" + name.toUpperCase() + "\n" + dob.toUpperCase() + "\n" + par.toUpperCase() + "\n" + email.toUpperCase() + "\n" + phone.toUpperCase() + "\n" + place.toUpperCase() + " ");
                    Main.setTextColor(Color.parseColor("#3c3c3c"));
                    Main.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
                    Main.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/bell.ttf"));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        Main.setStateListAnimator(null);
                    }

                    layout.addView(Main, lp);
                }

            } catch (Exception e) {
                // TODO: handle exception
                Log.e("log_tag", "Error Parsing Data " + e.toString());
                if(name=="") {
                    Toast.makeText(FirstYearEC.this, "No Internet Access", Toast.LENGTH_SHORT).show();
                }
            }finally {


            }
        }
    }
}