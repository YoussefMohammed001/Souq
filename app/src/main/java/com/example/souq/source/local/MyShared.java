package com.example.souq.source.local;

import android.content.Context;
import android.content.SharedPreferences;

public class MyShared {
    private  static SharedPreferences sharedPreferences = null;

    public static  void init(Context context){
        // init call it in the first activity
        if (sharedPreferences == null){
            sharedPreferences = context.getSharedPreferences("user",context.MODE_PRIVATE);

        }


    }

    public static void saveString(SharedKeys key, String value){
        sharedPreferences.edit().putString(key.name(), value).apply();
    }

    public static  String getString(SharedKeys key){
        return  sharedPreferences.getString(key.name(),"");
    }

    public static  void clear (){
       SharedPreferences.Editor editor = sharedPreferences.edit();
       editor.clear();
       editor.apply();
    }


}


