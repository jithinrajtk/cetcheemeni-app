package com.cetcheemeni;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Notifications2 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_notifications2);
        Bundle extras = getIntent().getExtras();
        if (null != extras && getIntent().getExtras().containsKey("message") && getIntent().getExtras().containsKey("author")) {

            TextView message = (TextView) findViewById(R.id.message);
            TextView author = (TextView) findViewById(R.id.author);
            message.setText(extras.getString("message"));
            author.setText(extras.getString("author"));
        }

    }
}