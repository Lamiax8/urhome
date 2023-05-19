package com.example.urhome;

import android.app.Application;
import android.content.SharedPreferences;

public class MyApplication extends Application {

    SharedPreferences mSharedPreferences;
    SharedPreferences.Editor mEditor;
    private static MyApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        mSharedPreferences = getSharedPreferences("users", MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
    }

    public static synchronized MyApplication getInstance() {
        return mInstance;
    }

    public void saveUserEmail(String uid){
        mEditor.putString("email",uid);
        mEditor.apply();
    }
    public String getUserEmail(){
        return mSharedPreferences.getString("email","");
    }
    public void removeUserEmail(){
        mEditor.remove("email");
    }


}