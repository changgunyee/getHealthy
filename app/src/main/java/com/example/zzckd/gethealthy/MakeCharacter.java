package com.example.zzckd.gethealthy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MakeCharacter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_make_character);

    }

    void goSignIn(View v) {
        overridePendingTransition(R.anim.rightin_activity,R.anim.not_move_activity);
        startActivity(new Intent(getApplicationContext(), SignIn.class));
    }
}