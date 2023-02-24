package com.example.sigma;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.sigma.database.DataBase;
import com.example.sigma.models.User;

public class UserProfile extends AppCompatActivity {
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        Bundle arguments = getIntent().getExtras();
        int userId = (int)arguments.get("id");

        user = DataBase.getUserById(userId);
    }
}