package com.mac.training.data1;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by User on 8/22/2016.
 */
public class DataFireBase extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
