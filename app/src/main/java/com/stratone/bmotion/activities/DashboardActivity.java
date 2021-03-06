package com.stratone.bmotion.activities;

import androidx.appcompat.app.AppCompatActivity;
/*import butterknife.BindView;
import butterknife.ButterKnife;*/
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessaging;
import com.squareup.picasso.Picasso;
import com.stratone.bmotion.R;
import com.stratone.bmotion.model.User;
import com.stratone.bmotion.response.ResponseUser;
import com.stratone.bmotion.rest.ApiClient;
import com.stratone.bmotion.rest.ApiInterface;
import com.stratone.bmotion.utils.SessionManager;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DashboardActivity extends AppCompatActivity {
    TextView eFullName, eDateNow, quota, purchasedBBM;
    LinearLayout input, support, profile, info, lnWallet, lnHistory, lnHome, lnProfile;
    SwipeRefreshLayout mSwipeRefreshLayout;
    ImageView imgProfile, imgProfileMenu;

    static   String strSDesc = "ShortDesc";
    static String strIncidentNo = "IncidentNo";
    static String strDesc="IncidentNo";
   /* @BindView(R.id.fullName)
    TextView eFullName;

    @BindView(R.id.dateNow)
    TextView eDateNow;

    @BindView(R.id.input)
    LinearLayout input;

    @BindView(R.id.profile)
    LinearLayout profile;

    @BindView(R.id.support)
    LinearLayout support;

    @BindView(R.id.Quota)
    TextView quota;

    @BindView(R.id.purchasedBBM)
    TextView purchasedBBM;*/

    ApiInterface apiService;
    User user;

    private boolean doubleBackToExitPressedOnce = false;
    private String urlImage = "https://www.google.com/images/srpr/logo11w.png";
    private static final String TAG = "DashboardActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        /*setContentView(R.layout.activity_dashboard);*/
        /*ButterKnife.bind(this);*/

        onNewIntent(getIntent());
        FirebaseMessaging.getInstance().subscribeToTopic("ServiceNow");

        NewInstance(getIntent());

        apiService = ApiClient.getClient().create(ApiInterface.class);

        final SessionManager sessionManager = new SessionManager(getApplicationContext());
        user = sessionManager.getUserDetails();
        SetInstance(user);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshDashboard();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
        refreshDashboard();
        input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user.getVerification() != null)
                {
                    if(user.getVerification().equals("N"))
                    {
                        Toast.makeText(DashboardActivity.this,getResources().getString(R.string.prompt_not_verify),Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Intent intent = new Intent(DashboardActivity.this, InputActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
                        startActivity(intent);
                        finish();
                    }
                }
                else {
                    Toast.makeText(DashboardActivity.this,getResources().getString(R.string.prompt_not_verify),Toast.LENGTH_SHORT).show();
                }
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, ProfileActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
                finish();
            }
        });

        support.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, SuggestionActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
                finish();
            }
        });

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, InfoActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
                finish();
            }
        });

        lnWallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManager.logoutUser();
            }
        });

        lnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, ProfileActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
                finish();
            }
        });

        lnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, DashboardActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
                finish();
            }
        });

        lnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, ProfileActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
                finish();
            }
        });
    }

    private void NewInstance(Intent intent)
    {
        Bundle extras = intent.getExtras();
        if (extras == null)
        {
            setContentView(R.layout.activity_dashboard);
            mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeLayout);
            eFullName = findViewById(R.id.fullName);
            eDateNow = findViewById(R.id.dateNow);
            quota = findViewById(R.id.Quota);
            purchasedBBM= findViewById(R.id.purchasedBBM);
            lnWallet = findViewById(R.id.lnWallet);
            lnHistory = findViewById(R.id.lnHistory);
            lnHome = findViewById(R.id.lnHome);
            lnProfile= findViewById(R.id.lnProfile);
            input = findViewById(R.id.input);
            support= findViewById(R.id.support);
            profile= findViewById(R.id.profile);
            info= findViewById(R.id.info);
            imgProfile = findViewById(R.id.imgProfile);
            imgProfileMenu = findViewById(R.id.imgProfileMenu);
        }
    }
    @Override
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        try {
            Bundle extras = intent.getExtras();
            if (extras != null) {
                setContentView(R.layout.activity_dashboard);
                intent = new Intent(DashboardActivity.this, InfoActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
                finish();
            }
        }catch (Exception e)
        {
            Log.d("Firebase Log",e.getMessage());
        }
    }

    private void SetInstance(User user)
    {
        eFullName.setText(user.getName());

        purchasedBBM.setText(user.getPurchaseBBM());

        if(user.getQuota() != null)
        {
            quota.setText(user.getQuota());
        }
        else
        {
            quota.setText("0 Ltr");
        }

        Picasso.get().load(user.getImageProfilePath()).transform(new RoundedTransformation()).into(imgProfile);
        Picasso.get().load(user.getImageProfilePath()).transform(new RoundedTransformation()).into(imgProfileMenu);

        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("MMM yyyy", Locale.getDefault());
        eDateNow.setText(df.format(c));
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            Intent startMain = new Intent(Intent.ACTION_MAIN);
            startMain.addCategory(Intent.CATEGORY_HOME);
            /*startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startMain.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);*/
            startActivity(startMain);

            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, getApplicationContext().getResources().getString(R.string.prompt_twice_exit), Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

    public void refreshDashboard()
    {
        apiService.limitquota(user.getNIP()).enqueue(new Callback<ResponseUser>() {
            @Override
            public void onResponse(Call<ResponseUser> call, Response<ResponseUser> response) {
                if(response.isSuccessful())
                {
                    if(response.body().getStatus().equals("success"))
                    {
                        user = response.body().getUser();
                        SessionManager sessionManager = new SessionManager(getApplicationContext());
                        sessionManager.createLoginSession(user);
                        SetInstance(user);
                    }
                    else {
                        Toast.makeText(DashboardActivity.this,response.body().getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
                else if(!response.isSuccessful())
                {
                    Toast.makeText(DashboardActivity.this,getApplicationContext().getResources().getString(R.string.prompt_failed_get_quota),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseUser> call, Throwable t) {
                Log.e(TAG,"onFailure: "+t.getLocalizedMessage());
                Toast.makeText(DashboardActivity.this,getApplicationContext().getResources().getString(R.string.connect_server_failed),Toast.LENGTH_SHORT).show();
            }
        });
    }
}
