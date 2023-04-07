package com.example.sigma.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.example.sigma.R;
import com.example.sigma.view.fragment.HeaderFragment;
import com.example.sigma.viewmodel.NotificationActivityViewModel;
import com.example.sigma.viewmodel.UserListActivityViewModel;

public class UserListActivity extends AppCompatActivity {
    private final String TAG = this.getClass().getSimpleName();
    FragmentManager fragmentManager;
    private UserListActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        viewModel = new ViewModelProvider(this).get(UserListActivityViewModel.class);
        viewModel.initViewModel(this);

        if(savedInstanceState == null) {
            fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().setReorderingAllowed(true).add(R.id.headerContainerView,
                    HeaderFragment.class, null).commit();

        }
    }
}