package com.stratone.bmotion.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.firebase.messaging.FirebaseMessaging;
import com.stratone.bmotion.R;

public class InfoActivity extends AppCompatActivity {

    static   String strSDesc = "ShortDesc";
    static String strIncidentNo = "IncidentNo";
    static String strDesc="IncidentNo";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        onNewIntent(getIntent());
        FirebaseMessaging.getInstance().
                subscribeToTopic("ServiceNow");
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onNewIntent(Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras != null) {
            setContentView(R.layout.activity_info);
            final TextView IncidentTextView =
                    (TextView) findViewById(R.id.txtIncidentNo);
            final TextView SDescTextView =
                    (TextView) findViewById(R.id.txtShortDesc);

            final TextView DescTextView =
                    (TextView) findViewById(R.id.txtDesc);
            strSDesc = extras.getString("ShortDesc",
                    "ShortDesc");
            strIncidentNo = extras.getString("IncidentNo",
                    "IncidentNo");
            strDesc = extras.getString("Description",
                    "IncidentNo");

            IncidentTextView.setText(strIncidentNo);
            SDescTextView.setText(strSDesc);
            DescTextView.setText(strDesc);
        }
    }

    public void browser1(View view){
        Intent browserIntent =new Intent
                (Intent.ACTION_VIEW, Uri.parse
                        ("https://somebrowser?uri=incident.do?sysparm_query=number="
                                +strIncidentNo));
        startActivity(browserIntent);
    }
}