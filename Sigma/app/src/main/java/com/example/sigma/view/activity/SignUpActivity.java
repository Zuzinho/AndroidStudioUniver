package com.example.sigma.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.example.sigma.R;
import com.example.sigma.view.fragment.HeaderFragment;
import com.example.sigma.viewmodel.NotificationActivityViewModel;
import com.example.sigma.viewmodel.SignUpActivityViewModel;

public class SignUpActivity extends AppCompatActivity {
    private final String TAG = this.getClass().getSimpleName();
    FragmentManager fragmentManager;
    private SignUpActivityViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        viewModel = new ViewModelProvider(this).get(SignUpActivityViewModel.class);
        viewModel.initViewModel(this);

        if(savedInstanceState == null){
            fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().setReorderingAllowed(true).add(R.id.headerContainerView,
                    HeaderFragment.class, null).commit();
        }

        Button signInButton = findViewById(R.id.signInButton);
        signInButton.setOnClickListener(v -> startActivity(new Intent(this, SignInActivity.class)));
    }
}