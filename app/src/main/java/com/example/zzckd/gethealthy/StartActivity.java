package com.example.zzckd.gethealthy;

import android.app.Activity;
import android.content.Intent;
import android.provider.MediaStore;
import android.os.Bundle;

public class StartActivity extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,1);
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        overridePendingTransition(R.anim.rightin_activity,R.anim.not_move_activity);
        startActivity(new Intent(getApplicationContext(),MakeCharacter.class));
    }
}
