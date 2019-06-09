package com.example.zzckd.gethealthy;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class loadingAvatar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_avatar);
        CustomRunnable mCustonRunnable=new CustomRunnable();
        Handler handler=new Handler();
        handler.postDelayed(mCustonRunnable,1000);
    }
    class CustomRunnable implements Runnable{
        @Override
        public void run() {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }
    }
}
