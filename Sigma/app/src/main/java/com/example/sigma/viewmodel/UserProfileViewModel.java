package com.example.sigma.viewmodel;

import android.content.Context;
import android.content.Intent;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sigma.model.models.User;
import com.example.sigma.model.database.DataBase;

public class UserProfileViewModel extends ViewModel {
    private Context context;
    private MutableLiveData<User> user;

    public void initViewModel(Context context, Intent intent){
        this.context = context;
        int userId = intent.getExtras().getInt("userId");
        this.user = new MutableLiveData<>(DataBase.getUserById(userId));
    }

    public boolean existUser(){
        return user != null;
    }

    public MutableLiveData<User> getUser(){
        return this.user;
    }
}
