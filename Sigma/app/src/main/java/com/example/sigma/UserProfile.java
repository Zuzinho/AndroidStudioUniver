package com.example.sigma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.util.Log;
import android.view.View;

import com.example.sigma.database.DataBase;
import com.example.sigma.databinding.ActivityMainBinding;
import com.example.sigma.databinding.ActivityUserProfileBinding;
import com.example.sigma.models.User;

public class UserProfile extends AppCompatActivity {
    private final String TAG = this.getClass().getSimpleName();
    private User user;
    private int userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle arguments = getIntent().getExtras();

        int id = (int)arguments.get("id");

        user = DataBase.getUserById(id);
        userId = (int)arguments.get("userid");

        ActivityUserProfileBinding view = ActivityUserProfileBinding.inflate(getLayoutInflater());

        view.userAvatar.setImageResource(user.getAvatarPath());
        view.userName.setText(user.getName());
        view.userPosition.setText(user.getPosition());
        view.userInfo.setText(user.getInfo());

        view.mainPageButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
    }

    public void onMyProfileClick(View view){
        if(userId == user.getId()) return;
        Intent intent = new Intent(this, UserProfile.class);
        intent.putExtra("id", userId);
        intent.putExtra("userId", userId);
        startActivity(intent);
    }
}