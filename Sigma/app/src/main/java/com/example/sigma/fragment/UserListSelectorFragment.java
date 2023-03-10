package com.example.sigma.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.sigma.R;
import com.example.sigma.database.DataBase;

public class UserListSelectorFragment extends Fragment {
    public UserListSelectorFragment(){
        super(R.layout.fragment_user_list_selector);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_list_selector, container, false);

        Button listViewSelectButton = view.findViewById(R.id.listViewSelectButton);
        Button recyclerViewSelectButton = view.findViewById(R.id.recyclerViewSelectButton);

        listViewSelectButton.setOnClickListener(v -> {
            getParentFragmentManager().beginTransaction().replace(R.id.contentFragmentContainer,
                    new UserListViewFragment(DataBase.users), "UserListViewFragment").commit();
        });

        return view;
    }
}