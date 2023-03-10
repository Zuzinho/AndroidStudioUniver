package com.example.sigma.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.widget.Toast;

import com.example.sigma.R;
import com.example.sigma.database.DataBase;
import com.example.sigma.fragment.HeaderFragment;
import com.example.sigma.models.User;

public class UserProfile extends AppCompatActivity {
    private final String TAG = this.getClass().getSimpleName();
    private User user;
    FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityUserProfileBinding view = ActivityUserProfileBinding.inflate(getLayoutInflater());
        setContentView(view.getRoot());

        if(savedInstanceState == null){
            fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().setReorderingAllowed(true).add(R.id.headerContainerView,
                    HeaderFragment.class, null).commit();
        }

        int userId = (int)getIntent().getExtras().get("userId");
        user = DataBase.getUserById(userId);

        if(user == null) {
            Toast.makeText(getApplicationContext(), "No such user", Toast.LENGTH_LONG).show();
            finish();
            return;
        }
        view.userAvatar.setImageResource(user.getAvatarPath());
        view.userName.setText(user.getName());
        view.userPosition.setText(user.getPosition());
        view.userInfo.setText(user.getInfo());
    }
}