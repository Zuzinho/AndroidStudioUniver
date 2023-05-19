package com.example.sigma.view.fragment;

import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sigma.R;
import com.example.sigma.view.activity.UserProfile;

public class UserRowFragment extends Fragment {
    private final String TAG = this.getClass().getSimpleName();

    public UserRowFragment(){
        super(R.layout.fragment_user_row);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){

        Bundle data = this.getArguments();
        
        View view = inflater.inflate(R.layout.fragment_user_row, container, false);

        LinearLayout userRow = view.findViewById(R.id.userRow);
        TextView userName = view.findViewById(R.id.userNameTextView);

        ImageView userAvatar = view.findViewById(R.id.userAvatarImageView);
        Drawable drawable = userAvatar.getDrawable();
        if (drawable instanceof Animatable){
            ((Animatable)userAvatar).start();
        }

        TextView userPosition = view.findViewById(R.id.userPositionTextView);

        userRow.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), UserProfile.class);
            int id = data.getInt(getString(R.string.user_id));
            intent.putExtra(getString(R.string.user_id), id);
            startActivity(intent);
        });

        userName.setText(data.getString(getString(R.string.user_name)));
        userPosition.setText(data.getString(getString(R.string.user_position)));

        return view;
    }


}