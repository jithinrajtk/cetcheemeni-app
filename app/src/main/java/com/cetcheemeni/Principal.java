package com.cetcheemeni;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Principal extends Activity {

    int width,height;
    Activity context;
    Button send,close,call;
    TextView t1;
    EditText name,mail,message;
    String n,e,m;
    ProgressDialog pd;
    String text="IN";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_principal);
        context=this;


        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        width = dm.widthPixels;
        height = dm.heightPixels;

        //getWindow().setLayout((int) (width * .8), LinearLayout.LayoutParams.WRAP_CONTENT);
        //Typeface normalone = Typeface.createFromAsset(getAssets(), "fonts/notti.ttf");
        Typeface caviar = Typeface.createFromAsset(getAssets(), "fonts/notti.ttf");
        Typeface rach = Typeface.createFromAsset(getAssets(), "fonts/rach.ttf");



        //Typeface icon = Typeface.createFromAsset(getAssets(), "fonts/notti.ttf");

        t1 = (TextView) findViewById(R.id.t1);
        send = (Button) findViewById(R.id.send);
        name = (EditText) findViewById(R.id.name);
        mail = (EditText) findViewById(R.id.mail);
        message = (EditText) findViewById(R.id.message);

        t1.setTypeface(rach);
        //name.setTypeface(normalone);
        //mail.setTypeface(normalone);
        //message.setTypeface(normalone);
        send.setTypeface(rach);


        /*close.setText("\uf00d");*/


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                n = name.getText().toString();
                e = mail.getText().toString();
                m = message.getText().toString();
                n = n.replaceAll(" ", "_");
                e = e.replaceAll(" ", "");
                m = m.replaceAll(" ", "_");
                if (n.length() < 4) {
                    Toast.makeText(Principal.this, "Invalid Name", Toast.LENGTH_SHORT).show();
                } else if (!e.toLowerCase().contains("@")) {
                    Toast.makeText(Principal.this, "Invalid Email Address", Toast.LENGTH_SHORT).show();
                } else if (m.length() < 10) {
                    Toast.makeText(Principal.this, "Message Should Contain At Least 10 Characters", Toast.LENGTH_SHORT).show();
                } else {
                    send(n, e, m);
                }
            }
        });

    }

    public int dpToPx(int dp) {
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        int px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }

    public Activity getContext() {
        return context;
    }

    public void send(String n, String e, String m){
        super.onStart();
        BackTask bt=new BackTask();
        bt.execute("http://db-cetkr.rhcloud.com/feedback.php?name="+n+"&email="+e+"&message="+m+"&submit=SEND");
    }

    private class BackTask extends AsyncTask<String,Integer,Void> {
        protected void onPreExecute(){
            super.onPreExecute();
            pd = new ProgressDialog(context);
            pd.setMessage("Please wait...");
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
                if(pd!=null) {
                    pd.dismiss();
                }
            }
            return null;
        }

        protected void onPostExecute(Void result) {
            if (pd != null) {
                pd.dismiss();
                if(text.toUpperCase().contains("YES")) {
                    Toast.makeText(Principal.this, "Email Sent", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else if(text.toUpperCase().contains("NO")) {
                    Toast.makeText(Principal.this, "Email Not Sent", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else if(text.toUpperCase().contains("IN"))
                    Toast.makeText(Principal.this, "No Internet Access", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
