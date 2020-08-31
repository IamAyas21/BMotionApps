package com.stratone.bmotion.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/*import com.google.firebase.messaging.FirebaseMessaging;*/
import com.stratone.bmotion.R;
import com.stratone.bmotion.adapter.InfoAdapter;
import com.stratone.bmotion.adapter.PurchaseHistoryAdapter;
import com.stratone.bmotion.model.Info;
import com.stratone.bmotion.model.PurchaseHistory;
import com.stratone.bmotion.model.User;
import com.stratone.bmotion.response.ResponseInfo;
import com.stratone.bmotion.response.ResponsePurchaseHistory;
import com.stratone.bmotion.rest.ApiClient;
import com.stratone.bmotion.rest.ApiInterface;
import com.stratone.bmotion.utils.SessionManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InfoActivity extends AppCompatActivity {

    ApiInterface apiService;
    SessionManager sessionManager;
    private User user;
    private ProgressDialog pDialog;
    private InfoAdapter adapter;
    private List<Info> infoList;

    ListView listView;
    LinearLayout lnWallet, lnHistory, lnHome, lnProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        listView = findViewById(R.id.lvInfo);
        lnWallet = findViewById(R.id.lnWallet);
        lnHistory = findViewById(R.id.lnHistory);
        lnHome = findViewById(R.id.lnHome);
        lnProfile= findViewById(R.id.lnProfile);

        apiService = ApiClient.getClient().create(ApiInterface.class);

        sessionManager = new SessionManager(getApplicationContext());
        sessionManager.checkLogin();
        user = sessionManager.getUserDetails();

        Info();

        lnWallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManager.logoutUser();
            }
        });

        lnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InfoActivity.this, ProfileActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
                finish();
            }
        });

        lnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InfoActivity.this, DashboardActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
                finish();
            }
        });

        lnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InfoActivity.this, ProfileActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
                finish();
            }
        });
    }

    private void Info()
    {
        pDialog = new ProgressDialog(InfoActivity.this);
        pDialog.setMessage(getResources().getString(R.string.prompt_loading));
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

        apiService.information().enqueue(new Callback<ResponseInfo>() {
            @Override
            public void onResponse(Call<ResponseInfo> call, Response<ResponseInfo> response) {
                if(response.body().getStatus().equals("success"))
                {
                    pDialog.dismiss();
                    infoList = response.body().getData();
                    adapter = new InfoAdapter(InfoActivity.this, R.layout.list_item_info, infoList);
                    listView.setAdapter(adapter);
                    adapter.setListViewHeightBasedOnChildren(listView);
                }
                else {
                    pDialog.dismiss();
                    Toast.makeText(InfoActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseInfo> call, Throwable t) {
                pDialog.dismiss();
                Toast.makeText(InfoActivity.this,getApplicationContext().getResources().getString(R.string.connect_server_failed),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void Back()
    {
        Intent i = new Intent(InfoActivity.this, DashboardActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(i);
        finish();
    }

    @Override
    public void onBackPressed() {
        Back();
    }
}