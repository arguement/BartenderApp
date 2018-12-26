package com.williams.jordan.bartenderapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public void bartenderLogin(View view){
        Intent intent = new Intent(this,Login.class);
        startActivity(intent);
    }
    public void clientLogin(View view){
      /*  Intent intent = new Intent(this,clientLogin.class);
        startActivity(intent);*/
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

}
