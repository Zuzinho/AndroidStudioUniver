package com.example.sigma.viewmodel;

import android.content.Context;
import android.content.Intent;

import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sigma.model.models.User;
import com.example.sigma.model.database.DataBase;
import com.example.sigma.view.adapter.UserRecyclerViewAdapter;

import java.util.ArrayList;

public class MainActivityViewModel extends ViewModel {
    private int userId;
    private ArrayList<User> users;
    private Context context;

    public void initViewModel(Context context,int userId){
        this.context = context;
        this.userId = userId;
        this.users = DataBase.users;
    }

    public void setRecyclerView(RecyclerView recyclerView){
        UserRecyclerViewAdapter adapter = new UserRecyclerViewAdapter(context, users);
        LinearLayoutManager manager = new LinearLayoutManager(context);

        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }

    public Intent getIntent(){
        Intent intent = new Intent();
        if(userId == 0){
            intent.putExtra("textViewContent", "Sign in");
            intent.putExtra("signedIn", false);
            return intent;
        }
        intent.putExtra("textViewContent", "My profile");
        intent.putExtra("userId", userId);
        intent.putExtra("signedIn", true);
        return intent;
    }
}
