package com.star.servicetest;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;


public class MyIntentService extends IntentService {

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d("MyIntentService",
                "Thread id is " + Thread.currentThread().getId() +
                        " Thread name is " + Thread.currentThread().getName());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.d("MyIntentService", "onDestroy executed");
    }
}
