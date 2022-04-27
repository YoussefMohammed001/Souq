package com.example.souq.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.souq.R;

import com.example.souq.databinding.ActivityLoginBinding;
import com.example.souq.models.response.loginResponse.LoginData;
import com.example.souq.models.response.loginResponse.LoginResponse;
import com.example.souq.source.local.MyShared;
import com.example.souq.source.local.SharedKeys;
import com.example.souq.source.network.MyRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
ActivityLoginBinding activityLoginBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityLoginBinding = DataBindingUtil.setContentView(this,R.layout.activity_login);
        setContentView(activityLoginBinding.getRoot());
        activityLoginBinding.btnLogin.setOnClickListener(view -> getDataFromUi());
        activityLoginBinding.btnRegister.setOnClickListener(view -> navigateToRegisterScreen());

    }

    private void navigateToRegisterScreen() {
        Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
        startActivity(intent);
    }

    private void getDataFromUi() {
        String email = activityLoginBinding.etEmail.getText().toString();
        if (email.isEmpty()){
            Toast.makeText(this, "email is required", Toast.LENGTH_SHORT).show();
            return;
        }

        String password = activityLoginBinding.etPassword.getText().toString();
        if (password.isEmpty()){
            Toast.makeText(this, "password is required", Toast.LENGTH_SHORT).show();
            return;
        }
        showProgressDialog();
        login(email,password);

    }

    private void login(String email, String password) {
        MyRetrofit.getApis().login(email,password)
                .enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                     dismissProgressDialog();
                      if (response.isSuccessful()){
                          handleResponse(response.body());



                      }else {
                          Toast.makeText(LoginActivity.this, "Try Again", Toast.LENGTH_SHORT).show();
                      }
                    }

                    @Override

                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                    dismissProgressDialog();
                        Toast.makeText(LoginActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }


    private void handleResponse(LoginResponse body) {
        if (body.getStatus()){
            saveUserData(body.getData());
           navigateToMainScreen();
        }
    }

    private void navigateToMainScreen() {
        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
        startActivity(intent);

    }

    public void saveUserData(LoginData data) {
        MyShared.saveString(SharedKeys.name,data.getName());
        MyShared.saveString(SharedKeys.email,data.getEmail());
        MyShared.saveString(SharedKeys.phoneNumber,data.getPhone());

    }
    ProgressDialog progressDialog;
    public void showProgressDialog(){
         progressDialog = ProgressDialog.show(
                this,
                "Signing you in",
                "Loading Please wait..."
                ,true);
    }

    private void dismissProgressDialog(){
        if (progressDialog != null &&  progressDialog.isShowing()){
            progressDialog.dismiss();
        }

    }

}