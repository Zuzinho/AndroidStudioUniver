package com.example.sigma;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;

public class UserListActivity extends AppCompatActivity {
    private final String TAG = this.getClass().getSimpleName();
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        if(savedInstanceState == null) {
            fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().setReorderingAllowed(true).add(R.id.headerContainerView,
                    HeaderFragment.class, null).commit();

        }
        fragmentManager.beginTransaction().setReorderingAllowed(true).add(R.id.contentFragmentContainer,
                UserListSelectorFragment.class, null).commit();
    }
}