package com.example.sigma.viewmodel;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.example.sigma.model.database.DataBase;

public class SignInActivityViewModel extends ViewModel {
    private Context context;

    public void initViewModel(Context context){
        this.context = context;

    }
}
