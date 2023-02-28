package com.example.sigma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.sigma.database.DataBase;
import com.example.sigma.databinding.ActivityMainBinding;
import com.example.sigma.databinding.ActivityUserProfileBinding;
import com.example.sigma.models.User;

public class UserProfile extends AppCompatActivity {
    private final String TAG = this.getClass().getSimpleName();
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityUserProfileBinding view = ActivityUserProfileBinding.inflate(getLayoutInflater());
        setContentView(view.getRoot());

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

        view.mainPageButton.setOnClickListener(v -> {
            Log.i(TAG, "Clicked on main page");

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });

    }
}