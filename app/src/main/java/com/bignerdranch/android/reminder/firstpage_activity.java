package com.bignerdranch.android.reminder;



import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;


public class firstpage_activity extends AppCompatActivity{

    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.first_page);

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(firstpage_activity.this,requireInfo_activity.class);
                startActivity(intent);
                finish();
            }
        },2000);


    }

}
