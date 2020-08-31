package com.stratone.bmotion.activities;
/*import butterknife.BindView;
import butterknife.ButterKnife;*/
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessaging;
import com.stratone.bmotion.R;
import com.stratone.bmotion.model.User;
import com.stratone.bmotion.response.ResponseUser;
import com.stratone.bmotion.rest.ApiClient;
import com.stratone.bmotion.rest.ApiInterface;
import com.stratone.bmotion.utils.SessionManager;

public class LoginActivity extends AbsRunTimePermission{
/*
    @BindView(R.id.edtUser)
    EditText etUser;

    @BindView(R.id.edtPassword)
    EditText etPassword;

    @BindView(R.id.btnLogin)
    Button login;

    @BindView(R.id.txvSignUp)
    TextView signUp;
*/

    ApiInterface apiService;

    private static final String TAG = "LoginActivity";
    private static final int REQUEST_PERMISSION = 10;
    private ProgressDialog pDialog;

    EditText etUser, etPassword;
    Button login;
    TextView signUp;

    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        /*ButterKnife.bind(this);*/

        etUser = findViewById(R.id.edtUser);
        etPassword = findViewById(R.id.edtPassword);
        login = findViewById(R.id.btnLogin);
        signUp = findViewById(R.id.txvSignUp);

        SessionManager sessionManager = new SessionManager(getApplicationContext());
        if(sessionManager.isLoggedIn())
        {
            Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(intent);
            finish();
        }

        //request permission here
        requestAppPermissions(new String[]{
                        Manifest.permission.CAMERA,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION},
                R.string.msg,REQUEST_PERMISSION);

        apiService = ApiClient.getClient().create(ApiInterface.class);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ValidateLogin())
                {
                    loginUser();
                }
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onPermissionGranted(int requestcode) {
        /*Toast.makeText(getApplicationContext(), "Permission granted", Toast.LENGTH_LONG).show();*/
    }

    private void loginUser()
    {
        pDialog = new ProgressDialog(LoginActivity.this);
        pDialog.setMessage(getResources().getString(R.string.prompt_loading));
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

        String userName = etUser.getText().toString();
        String password = etPassword.getText().toString();
        apiService.login(userName,password).enqueue(new Callback<ResponseUser>() {
            @Override
            public void onResponse(Call<ResponseUser> call, Response<ResponseUser> response) {
                if(response.isSuccessful())
                {
                    if(response.body().getStatus().equals("success"))
                    {
                        pDialog.dismiss();
                        User userLoggedIn = response.body().getUser();

                        SessionManager sessionManager = new SessionManager(getApplicationContext());
                        sessionManager.createLoginSession(userLoggedIn);

                        Intent intent = new Intent(LoginActivity.this,DashboardActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
                        Toast.makeText(LoginActivity.this,response.body().getMessage(),Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                        finish();
                    }
                    else {
                        pDialog.dismiss();
                        Toast.makeText(LoginActivity.this,response.body().getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
                else if(!response.isSuccessful())
                {
                    pDialog.dismiss();
                    Toast.makeText(LoginActivity.this,getApplicationContext().getResources().getString(R.string.login_failed),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseUser> call, Throwable t) {
                pDialog.dismiss();
                Log.e(TAG,"onFailure: "+t.getLocalizedMessage());
                Toast.makeText(LoginActivity.this,getApplicationContext().getResources().getString(R.string.connect_server_failed),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean ValidateLogin()
    {
        boolean isSuccess = false;
        if(!etUser.getText().toString().equals(""))
        {
            if(!etPassword.getText().toString().equals(""))
            {
                isSuccess = true;
            }
            else
            {
                etPassword.setError(getResources().getString(R.string.error_cant_empty));
                etPassword.requestFocus();
            }
        }
        else
        {
            etUser.setError(getResources().getString(R.string.error_cant_empty));
            etUser.requestFocus();
        }
        return isSuccess;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
