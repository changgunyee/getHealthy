package com.example.zzckd.gethealthy.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.example.zzckd.gethealthy.R;

public class CustomDIalog extends Dialog {
    public CustomDIalog(Context context) {
        super(context);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_dialog);


    }


}
