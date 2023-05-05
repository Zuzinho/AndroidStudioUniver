package com.example.sigma.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Toast;

import com.example.sigma.R;
import com.example.sigma.databinding.ActivityUserProfileBinding;
import com.example.sigma.view.fragment.HeaderFragment;
import com.example.sigma.model.models.User;
import com.example.sigma.viewmodel.UserProfileViewModel;

public class UserProfile extends AppCompatActivity {
    private final String TAG = this.getClass().getSimpleName();
    FragmentManager fragmentManager;
    private UserProfileViewModel viewModel;
    ActivityUserProfileBinding view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = ActivityUserProfileBinding.inflate(getLayoutInflater());
        setContentView(view.getRoot());
        viewModel = new ViewModelProvider(this).get(UserProfileViewModel.class);
        viewModel.initViewModel(this, getIntent());

        if(savedInstanceState == null){
            fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().setReorderingAllowed(true).add(R.id.headerContainerView,
                    HeaderFragment.class, null).commit();
        }


        if(!viewModel.existUser()) {
            Toast.makeText(getApplicationContext(), "No such user", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        viewModel.getUser().observe(this, this::setView);
    }

    private void setView(User user){
        view.userAvatar.setImageResource(user.getAvatarPath());
        view.userName.setText(user.getName());
        view.userPosition.setText(user.getPosition());
        view.userInfo.setText(user.getInfo());
    }
}