package com.horus_vision.agrid.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.horus_vision.agrid.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        final Handler mHandler = new Handler();
        mHandler.postDelayed(() -> {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            this.finish();
        }, 1500);

    }


}
