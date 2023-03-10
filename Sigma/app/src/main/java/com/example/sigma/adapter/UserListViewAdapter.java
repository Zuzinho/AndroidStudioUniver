package com.example.sigma.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.sigma.R;
import com.example.sigma.activity.UserProfile;
import com.example.sigma.models.User;

import java.util.List;
import java.util.zip.Inflater;

public class UserListViewAdapter extends ArrayAdapter<User> {
    private final String TAG = this.getClass().getSimpleName();
    private LayoutInflater inflater;
    private int layout;
    private List<User> users;

    public UserListViewAdapter(@NonNull Context context, int resource, @NonNull List<User> objects) {
        super(context, resource, objects);
        inflater = LayoutInflater.from(context);
        layout = resource;
        users = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = inflater.inflate(layout, parent, false);
        User user = users.get(position);

        LinearLayout userRow = view.findViewById(R.id.userRow);
        TextView userName = view.findViewById(R.id.userNameTextView);
        ImageView userAvatar = view.findViewById(R.id.userAvatarImageView);
        TextView userPosition = view.findViewById(R.id.userPositionTextView);

        userRow.setOnClickListener(v -> {
            Log.i(TAG, "Clicked user`s portfolio");
        });

        userName.setText(user.getName());
        userAvatar.setImageResource(user.getAvatarPath());
        userPosition.setText(user.getPosition());

        return view;
    }
}
