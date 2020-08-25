package com.stratone.bmotion.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.stratone.bmotion.R;
import com.stratone.bmotion.utils.SessionManager;

public class FAQActivity extends AppCompatActivity {
    LinearLayout lnWallet, lnHistory, lnHome, lnProfile,
                    lnQuestion1, lnQuestion2, lnQuestion3, lnQuestion4,lnQuestion5,
                    lnAnswer1, lnAnswer2, lnAnswer3, lnAnswer4,lnAnswer5,
                    down1,down2,down3,down4,down5, top1, top2, top3, top4, top5;

    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_f_a_q);

        lnWallet = findViewById(R.id.lnWallet);
        lnHistory = findViewById(R.id.lnHistory);
        lnHome = findViewById(R.id.lnHome);
        lnProfile= findViewById(R.id.lnProfile);

        lnQuestion1 = findViewById(R.id.lnQuestion1);
        lnQuestion2 = findViewById(R.id.lnQuestion2);
        lnQuestion3 = findViewById(R.id.lnQuestion3);
        lnQuestion4 = findViewById(R.id.lnQuestion4);
        lnQuestion5 = findViewById(R.id.lnQuestion5);

        lnAnswer1 = findViewById(R.id.lnAnswer1);
        lnAnswer2 = findViewById(R.id.lnAnswer2);
        lnAnswer3 = findViewById(R.id.lnAnswer3);
        lnAnswer4 = findViewById(R.id.lnAnswer4);
        lnAnswer5 = findViewById(R.id.lnAnswer5);

        down1 = findViewById(R.id.down1);
        down2 = findViewById(R.id.down2);
        down3 = findViewById(R.id.down3);
        down4 = findViewById(R.id.down4);
        down5 = findViewById(R.id.down5);

        top1 = findViewById(R.id.top1);
        top2 = findViewById(R.id.top2);
        top3 = findViewById(R.id.top3);
        top4 = findViewById(R.id.top4);
        top5 = findViewById(R.id.top5);

        sessionManager = new SessionManager(getApplicationContext());

        lnWallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManager.logoutUser();
            }
        });

        lnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FAQActivity.this, ProfileActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
                finish();
            }
        });

        lnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FAQActivity.this, DashboardActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
                finish();
            }
        });

        lnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FAQActivity.this, ProfileActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
                finish();
            }
        });

        lnQuestion1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(lnAnswer1.getVisibility() == View.GONE)
                {
                    Open(down1,top1,lnAnswer1);
                }
                else
                {
                    Closed(down1,top1,lnAnswer1);
                }
            }
        });

        lnQuestion2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(lnAnswer2.getVisibility() == View.GONE)
                {
                    Open(down2,top2,lnAnswer2);
                }
                else
                {
                    Closed(down2,top2,lnAnswer2);
                }
            }
        });

        lnQuestion3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(lnAnswer3.getVisibility() == View.GONE)
                {
                    Open(down3,top3,lnAnswer3);
                }
                else
                {
                    Closed(down3,top3,lnAnswer3);
                }
            }
        });

        lnQuestion4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(lnAnswer4.getVisibility() == View.GONE)
                {
                    Open(down4,top4,lnAnswer4);
                }
                else
                {
                    Closed(down4,top4,lnAnswer4);
                }
            }
        });

        lnQuestion5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(lnAnswer5.getVisibility() == View.GONE)
                {
                    Open(down5,top5,lnAnswer5);
                }
                else
                {
                    Closed(down5,top5,lnAnswer5);
                }
            }
        });

        down1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Open(down1,top1,lnAnswer1);
            }
        });

        down2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Open(down2,top2,lnAnswer2);
            }
        });

        down3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Open(down3,top3,lnAnswer3);
            }
        });

        down4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Open(down4,top4,lnAnswer4);
            }
        });

        down5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Open(down5,top5,lnAnswer5);
            }
        });

        top1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Closed(down1,top1,lnAnswer1);
            }
        });

        top2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Closed(down2,top2,lnAnswer2);
            }
        });

        top3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Closed(down3,top3,lnAnswer3);
            }
        });

        top4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Closed(down4,top4,lnAnswer4);
            }
        });

        top5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Closed(down5,top5,lnAnswer5);
            }
        });
    }

    private void Open(LinearLayout down, LinearLayout top, LinearLayout answer)
    {
        down.setVisibility(View.GONE);
        top.setVisibility(View.VISIBLE);
        answer.setVisibility(View.VISIBLE);
    }

    private void Closed(LinearLayout down, LinearLayout top, LinearLayout answer)
    {
        down.setVisibility(View.VISIBLE);
        top.setVisibility(View.GONE);
        answer.setVisibility(View.GONE);
    }

    private void Back()
    {
        Intent i = new Intent(FAQActivity.this, SupportActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(i);
        finish();
    }

    @Override
    public void onBackPressed() {
        Back();
    }
}