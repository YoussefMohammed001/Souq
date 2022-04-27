package com.example.souq.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.souq.R;
import com.example.souq.databinding.ItemCategoryBinding;
import com.example.souq.models.response.categoryResponse.Datum;

import java.util.List;

public class CategoryAdapter extends  RecyclerView.Adapter<CategoryAdapter.CategoryHolder> {
    ItemCategoryBinding binding;
    List<Datum> data;

    public CategoryAdapter(ItemCategoryBinding binding) {
        this.binding = binding;

    }

    public CategoryAdapter(List<Datum> data) {
        this.binding = binding;
        this.data = data;
    }

    @NonNull
    @Override
    public CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CategoryHolder(DataBindingUtil.inflate(LayoutInflater
                        .from(parent.getContext()),
                R.layout.item_category,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryHolder holder, int position) {
Datum datum = data.get(position);
holder.binding.textItem.setText(datum.getName());
holder.binding.setDatum(datum);

        Glide.with(holder.itemView).load(datum.getImage())
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.binding.imageItem);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    class CategoryHolder extends RecyclerView.ViewHolder{
ItemCategoryBinding binding;
        public CategoryHolder(@NonNull ItemCategoryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
