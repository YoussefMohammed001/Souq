package com.example.souq.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.souq.Adapter.CategoryAdapter;
import com.example.souq.R;
import com.example.souq.databinding.FragmentCatBinding;
import com.example.souq.models.response.categoryResponse.CategoryResponse;
import com.example.souq.source.network.Apis;
import com.example.souq.source.network.MyRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class CatFragment extends Fragment {
FragmentCatBinding fragmentCatBinding;
    CategoryAdapter adapter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      fragmentCatBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_cat,container,false);
      return fragmentCatBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

     category();





    }

    private void category() {
        MyRetrofit.getApis().category()
                .enqueue(new Callback<CategoryResponse>() {
                    @Override
                    public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                        if (response.isSuccessful()){
                           adapter = new CategoryAdapter(response.body().getData().getData());
                           fragmentCatBinding.recyclerView.setAdapter(adapter);
                        }
                    }

                    @Override
                    public void onFailure(Call<CategoryResponse> call, Throwable t) {

                    }
                });
    }


}