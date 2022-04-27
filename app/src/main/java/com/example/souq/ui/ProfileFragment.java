package com.example.souq.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.souq.R;
import com.example.souq.databinding.FragmentProfileBinding;
import com.example.souq.source.local.MyShared;
import com.example.souq.source.local.SharedKeys;


public class ProfileFragment extends Fragment {

FragmentProfileBinding fragmentProfileBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentProfileBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile,container,false);
        return fragmentProfileBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
fragmentProfileBinding.userName.setText(MyShared.getString(SharedKeys.name));
fragmentProfileBinding.userEmail.setText(MyShared.getString(SharedKeys.email));
fragmentProfileBinding.userPhone.setText(MyShared.getString(SharedKeys.phoneNumber));

fragmentProfileBinding.btnLogOut.setOnClickListener( view1 -> logOut());



    }

    private void logOut() {
        showProgressDialog();
        MyShared.clear();
        dismissProgressDialog();
        Intent intent = new Intent(getActivity(),LoginActivity.class);
startActivity(intent);

    }


    ProgressDialog progressDialog;
    public void showProgressDialog(){
        progressDialog = ProgressDialog.show(
                getActivity(),
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