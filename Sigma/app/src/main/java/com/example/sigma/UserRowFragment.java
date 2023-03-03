package com.example.sigma;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sigma.models.User;

public class UserRowFragment extends Fragment {
    private final String TAG = this.getClass().getSimpleName();
    private User user;

    public UserRowFragment(User user){
        super(R.layout.fragment_user_row);
        this.user = user;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.fragment_user_row, container, false);

        LinearLayout userRow = view.findViewById(R.id.userRow);
        TextView userName = view.findViewById(R.id.userNameTextView);
        ImageView userAvatar = view.findViewById(R.id.userAvatarImageView);
        TextView userPosition = view.findViewById(R.id.userPositionTextView);

        userRow.setOnClickListener(v -> {
            Log.i(TAG, "Clicked user`s portfolio");

            Intent intent = new Intent(getContext(), UserProfile.class);
            int id = user.getId();
            intent.putExtra("userId", id);
            startActivity(intent);
        });

        userName.setText(user.getName());
        userAvatar.setImageResource(user.getAvatarPath());
        userPosition.setText(user.getPosition());

        return view;
    }


}