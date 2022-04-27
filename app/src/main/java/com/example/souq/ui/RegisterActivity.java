package com.example.souq.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.souq.R;

import com.example.souq.databinding.ActivityRegisterBinding;
import com.example.souq.models.response.registerResponse.RegisterResponse;
import com.example.souq.source.network.MyRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    ActivityRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this,R.layout.activity_register);
        setContentView(binding.getRoot());
        binding.registerBtn.setOnClickListener(view -> getDataFromUi() );
    }

    private void getDataFromUi() {
        String name = binding.etName.getText().toString();
        if (name.isEmpty()){
            Toast.makeText(this, "name is required", Toast.LENGTH_SHORT).show();
            return;
        }

        String email = binding.etEmail.getText().toString();
        if (email.isEmpty()){
            Toast.makeText(this, "email is required", Toast.LENGTH_SHORT).show();
            return;
        }

        String phoneNumber = binding.etPhone.getText().toString();
        if (phoneNumber.isEmpty()){
            Toast.makeText(this, "phone number is required", Toast.LENGTH_SHORT).show();
            return;
        }

        String password = binding.etPassword.getText().toString();
        if (password.isEmpty()){
            Toast.makeText(this, "password is required", Toast.LENGTH_SHORT).show();
            return;
        }
        showProgressDialog();
        register(name,email,password,phoneNumber);

    }

    private void register(String name, String email, String password, String phoneNumber) {
        MyRetrofit.getApis().register(email,name,password,phoneNumber).enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
               if (response.isSuccessful()){
                   dismissProgressDialog();
                   handleResponse(response.body());
               } else
                   Toast.makeText(RegisterActivity.this, "try Again", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void handleResponse(RegisterResponse body) {
        if (body.getStatus()){
            navigateToLoginScreen();
        } else
            Toast.makeText(this, body.getMessage(), Toast.LENGTH_SHORT).show();
    }

    private void navigateToLoginScreen() {

        Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
        startActivity(intent);

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