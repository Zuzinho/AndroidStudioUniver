package com.example.sigma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;

public class SignInActivity extends AppCompatActivity {
    private final String TAG = this.getClass().getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        LinearLayout mainPageButton = (LinearLayout)findViewById(R.id.mainPageButton);
        mainPageButton.setOnClickListener(v -> {
            Log.i(TAG, "Clicked main page");
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });

        Button signUpButton = (Button)findViewById(R.id.signUpButton);
        signUpButton.setOnClickListener(v -> {
            Log.i(TAG, "Clicked sign up button");
            Intent intent = new Intent(this, SignUpActivity.class);
            startActivity(intent);
        });
    }
}