package com.example.sigma;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class SignUpActivity extends AppCompatActivity {
    private final String TAG = this.getClass().getSimpleName();
    FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        if(savedInstanceState == null){
            fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().setReorderingAllowed(true).add(R.id.headerContainerView,
                    HeaderFragment.class, null).commit();
        }

        Button signInButton = findViewById(R.id.signInButton);
        signInButton.setOnClickListener(v -> {
            Log.i(TAG, "Clicked sign in button");

            Intent intent = new Intent(this, SignInActivity.class);
            startActivity(intent);
        });
    }
}