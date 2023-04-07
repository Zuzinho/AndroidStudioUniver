package com.example.sigma.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sigma.R;
import com.example.sigma.view.adapter.UserListViewAdapter;
import com.example.sigma.model.database.DataBase;
import com.example.sigma.model.User;

import java.util.ArrayList;

public class UserListViewFragment extends Fragment {
    private ArrayList<User> users;
    public UserListViewFragment() {
        super(R.layout.fragment_user_list_view);
        this.users = DataBase.users;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_list_view, container, false);

        ListView listView = view.findViewById(R.id.userListView);

        String path = getArguments().getString("path");

        UserListViewAdapter adapter = new UserListViewAdapter(getContext(), R.layout.fragment_user_row, users);

        listView.setAdapter(adapter);

        return view;
    }
}