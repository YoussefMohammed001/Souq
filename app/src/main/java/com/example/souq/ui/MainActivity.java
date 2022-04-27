package com.example.souq.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.souq.R;
import com.example.souq.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
ActivityMainBinding activityMainBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        replaceFragment(new HomeFragment());

    activityMainBinding.bottomNavigation.setOnItemSelectedListener(item ->{
        switch (item.getItemId()){
            case R.id.home:
                replaceFragment(new HomeFragment());
                break;
            case R.id.categorize:
                replaceFragment(new CatFragment());
                break;
            case R.id.favourites:
                replaceFragment(new FavFragment());
                break;
            case R.id.profile:
                replaceFragment(new ProfileFragment());
                break;

        }
        return  true;
    });

    }

    private void replaceFragment(Fragment Fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame,Fragment);
        fragmentTransaction.commit();
    }

    @BindingAdapter("glide")
    public static void glide (ImageView imageView, String imageUrl){
        Glide.with(imageView.getContext())
                .load(imageUrl)
                .placeholder(R.mipmap.ic_launcher)
                .into(imageView);
}



}