package com.dhruvarora.guftagu;

import android.content.Intent;
import android.os.Handler;
//import android.support.v7.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                startActivity(new Intent(MainActivity.this, LoginActivity.class));
//            }
//        }, 2000);
        Thread td = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(3000);
                } catch (Exception ex){
                    ex.printStackTrace();
                } finally {
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }; td.start();
    }
}