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
import android.widget.Toast;

import com.example.sigma.models.User;

public class UserRowFragment extends Fragment {
    private final String TAG = this.getClass().getSimpleName();

    public UserRowFragment(){
        super(R.layout.fragment_user_row);
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        getParentFragmentManager().setFragmentResultListener("requestKey", this,
                (requestKey, bundle) -> {
                    String result = bundle.getString("userPage");
                    Log.i(TAG, "User page - " + result);
                }
        );
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){

        Bundle data = this.getArguments();
        
        View view = inflater.inflate(R.layout.fragment_user_row, container, false);

        LinearLayout userRow = view.findViewById(R.id.userRow);
        TextView userName = view.findViewById(R.id.userNameTextView);
        ImageView userAvatar = view.findViewById(R.id.userAvatarImageView);
        TextView userPosition = view.findViewById(R.id.userPositionTextView);

        userRow.setOnClickListener(v -> {
            Log.i(TAG, "Clicked user`s portfolio");

            Bundle result = new Bundle();
            result.putString("userPage", "On user page");
            getParentFragmentManager().setFragmentResult("requestKey", result);
            Intent intent = new Intent(getContext(), UserProfile.class);
            int id = data.getInt("userId");
            intent.putExtra("userId", id);
            startActivity(intent);
        });

        userName.setText(data.getString("userName"));
        userAvatar.setImageResource(data.getInt("userAvatar"));
        userPosition.setText(data.getString("userPosition"));

        return view;
    }


}