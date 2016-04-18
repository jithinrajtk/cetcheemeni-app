package com.cetcheemeni;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Events extends AppCompatActivity {

    Activity context;
    ProgressDialog pd;
    String s[] = new String[1000];
    String head[] = new String[1000];
    String dt[] = new String[1000];

    Button content[]=new Button[1000];
    Button heading[]= new Button[1000];

    Button d[]= new Button[1000];
    JSONArray jArray;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        getSupportActionBar().setElevation(0);

        context=this;
        s[0]="";
        head[0]="";
    }

    public void onStart(){
        super.onStart();
        BackTask bt=new BackTask();
        bt.execute("http://db-cetkr.rhcloud.com/events.php");
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
            //pd.setTitle("Loading Database");
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

                for(int i=0; i<jArray.length();i++){
                    JSONObject json = jArray.getJSONObject(i);
                    s[i] = json.getString("message");
                    head[i] = json.getString("title");
                }



                LinearLayout layout = (LinearLayout) findViewById(R.id.noti);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                lp.setMargins(0, 0, 0, dpToPx(0));

                LinearLayout.LayoutParams lpp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                lpp.setMargins(0, 0, 0, dpToPx(0));


                int i;
                for(i=jArray.length()-1;i>=0; i--) {

                    //heading

                    heading[i] = new Button(Events.this);
                    heading[i].setBackgroundResource(R.drawable.boxh);
                    heading[i].setText(head[i]);
                    heading[i].setTextColor(Color.parseColor("#ffffff"));
                    heading[i].setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
                    heading[i].setGravity(Gravity.CENTER);
                    heading[i].setPadding(dpToPx(5), 0, 0, 0);
                    layout.addView(heading[i], lpp);
                    //message

                    content[i] = new Button(Events.this);
                    content[i].setBackgroundResource(R.drawable.boxc);
                    content[i].setText(s[i]);
                    content[i].setTextColor(Color.parseColor("#3c3c3c"));
                    content[i].setTextSize(TypedValue.COMPLEX_UNIT_SP, 13);
                    content[i].setGravity(Gravity.LEFT | Gravity.CENTER);
                    content[i].setPadding(dpToPx(10), dpToPx(10), dpToPx(10), dpToPx(10));
                    layout.addView(content[i], lp);
                }

            } catch (Exception e) {
                // TODO: handle exception
                Log.e("log_tag", "Error Parsing Data " + e.toString());
                if(s[0]=="") {
                    Toast.makeText(Events.this, "No Internet Access", Toast.LENGTH_SHORT).show();
                }
            }finally {

            }
        }
    }
}