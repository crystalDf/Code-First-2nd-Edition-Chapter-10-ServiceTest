package com.star.servicetest;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private MyService.DownloadBinder mDownloadBinder;

    private ServiceConnection mServiceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mDownloadBinder = (MyService.DownloadBinder) service;
            mDownloadBinder.startDownload();
            mDownloadBinder.getProgress();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    private Button mStartService;
    private Button mStopService;
    private Button mBindService;
    private Button mUnbindService;
    private Button mStartIntentService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mStartService = findViewById(R.id.start_service);
        mStopService = findViewById(R.id.stop_service);
        mBindService = findViewById(R.id.bind_service);
        mUnbindService = findViewById(R.id.unbind_service);
        mStartIntentService = findViewById(R.id.start_intent_service);

        mStartService.setOnClickListener(v -> {
            Intent startIntent = new Intent(this, MyService.class);
            startService(startIntent);
        });

        mStopService.setOnClickListener(v -> {
            Intent stopIntent = new Intent(this, MyService.class);
            stopService(stopIntent);
        });

        mBindService.setOnClickListener(v -> {
            Intent bindIntent = new Intent(this, MyService.class);
            bindService(bindIntent, mServiceConnection, BIND_AUTO_CREATE);
        });

        mUnbindService.setOnClickListener(v -> unbindService(mServiceConnection));

        mStartIntentService.setOnClickListener(v -> {
            Log.d("MainActivity",
                    "Thread id is " + Thread.currentThread().getId() +
                            " Thread name is " + Thread.currentThread().getName());

            Intent intentService = new Intent(this, MyIntentService.class);
            startService(intentService);
        });
    }
}
