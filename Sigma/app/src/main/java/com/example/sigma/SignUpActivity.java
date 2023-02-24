package com.example.sigma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;

public class SignUpActivity extends AppCompatActivity {
    private final String TAG = this.getClass().getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        LinearLayout mainPageButton = (LinearLayout)findViewById(R.id.mainPageButton);
        mainPageButton.setOnClickListener(v -> {
            Log.i(TAG, "Clicked main page");

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });

        Button signInButton = (Button)findViewById(R.id.signInButton);
        signInButton.setOnClickListener(v -> {
            Log.i(TAG, "Clicked sign in button");

            Intent intent = new Intent(this, SignInActivity.class);
            startActivity(intent);
        });
    }
}