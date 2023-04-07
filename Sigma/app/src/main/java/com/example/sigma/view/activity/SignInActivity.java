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
import com.example.sigma.viewmodel.SignInActivityViewModel;

public class SignInActivity extends AppCompatActivity {
    private final String TAG = this.getClass().getSimpleName();
    FragmentManager fragmentManager;
    private SignInActivityViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        viewModel = new ViewModelProvider(this).get(SignInActivityViewModel.class);
        viewModel.initViewModel(this);

        if(savedInstanceState == null){
            fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().setReorderingAllowed(true).add(R.id.headerContainerView,
                    HeaderFragment.class, null).commit();
        }

        Button signUpButton = findViewById(R.id.signUpButton);
        signUpButton.setOnClickListener(v -> startActivity(new Intent(this, SignUpActivity.class)));

        Button signInButton = findViewById(R.id.signInButton);
        signInButton.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.putExtra(getString(R.string.user_id), 1);
            setResult(RESULT_OK, intent);
            finish();
        });
    }
}