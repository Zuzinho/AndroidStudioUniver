package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Toast.makeText(getApplicationContext(), "Created", Toast.LENGTH_SHORT).show();
        Log.i(TAG, "OnCreate");

        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart(){
        super.onStart();

        Toast.makeText(getApplicationContext(), "Started", Toast.LENGTH_SHORT).show();
        Log.i(TAG, "OnStart");
    }

    @Override
    protected void onResume(){
        super.onResume();

        Toast.makeText(getApplicationContext(), "Resumed", Toast.LENGTH_SHORT).show();
        Log.i(TAG, "OnResume");
    }

    @Override
    protected void onPause(){
        super.onPause();

        Toast.makeText(getApplicationContext(), "Paused", Toast.LENGTH_SHORT).show();
        Log.i(TAG, "OnPause");
    }

    @Override
    protected void onRestart(){
        super.onRestart();

        Toast.makeText(getApplicationContext(), "Restarted", Toast.LENGTH_SHORT).show();
        Log.i(TAG, "OnRestart");
    }

    @Override
    protected void onStop(){
        super.onStop();

        Toast.makeText(getApplicationContext(), "Stopped", Toast.LENGTH_SHORT).show();
        Log.i(TAG, "OnStop");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();

        Toast.makeText(getApplicationContext(), "Destroyed", Toast.LENGTH_SHORT).show();
        Log.i(TAG, "OnDestroy");
    }

    public void onClick(View view){
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }

}