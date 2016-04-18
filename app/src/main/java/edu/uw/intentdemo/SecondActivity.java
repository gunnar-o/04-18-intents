package edu.uw.intentdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    private static final String TAG = "**Second**";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        //action bar "back"
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Gets the intent that started this activity and the extras bundle
        Bundle extras = getIntent().getExtras();

        String value = null;
        if (extras != null) {
            value = extras.getString("edu.uw.intentdemo.message");
        }

        if (value != null) {
            TextView subtitle = (TextView) findViewById(R.id.txtSecond);
            subtitle.setText(value);
        }

    }
}
