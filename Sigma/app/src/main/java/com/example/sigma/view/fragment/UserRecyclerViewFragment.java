package com.example.sigma.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sigma.R;
import com.example.sigma.view.adapter.UserRecyclerViewAdapter;
import com.example.sigma.model.database.DataBase;
import com.example.sigma.model.models.User;

import java.util.ArrayList;

public class UserRecyclerViewFragment extends Fragment {
    private ArrayList<User> users;

    public UserRecyclerViewFragment(){
        super(R.layout.fragment_user_recycler_view);
        this.users = DataBase.users;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_recycler_view, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.userRecyclerView);

        String path = getArguments().getString("path");

        UserRecyclerViewAdapter adapter = new UserRecyclerViewAdapter(getContext(), users);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        return view;
    }
}