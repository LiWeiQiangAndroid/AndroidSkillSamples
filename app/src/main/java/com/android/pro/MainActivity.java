package com.android.pro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.android.pro.restart.RestartAPPTool;
import com.android.pro.restart.RestartIntentService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void restart(View view){
        RestartAPPTool.restartAPP(this);
        System.exit(0);
    }

    public void restart2(View view){
        RestartIntentService.restartAPP(this);
        System.exit(0);

    }
}
