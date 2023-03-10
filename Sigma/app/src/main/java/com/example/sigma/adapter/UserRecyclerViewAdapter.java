package com.example.sigma.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sigma.R;
import com.example.sigma.models.User;

import java.util.ArrayList;

public class UserRecyclerViewAdapter extends
        RecyclerView.Adapter<UserRecyclerViewAdapter.UserViewHolder> {
    private final String TAG = this.getClass().getSimpleName();
    private LayoutInflater inflater;
    private ArrayList<User> users;

    public UserRecyclerViewAdapter(Context context, ArrayList<User> users){
        Log.i(TAG, "UserRecyclerViewAdapter");
        this.inflater = LayoutInflater.from(context);
        this.users = users;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.i(TAG, "onCreateViewHolder");
        View view = inflater.inflate(R.layout.fragment_user_row, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        Log.i(TAG, "onBindViewHolder");
        User user = users.get(position);
        holder.userAvatarImageView.setImageResource(user.getAvatarPath());
        holder.userNameTextView.setText(user.getName());
        holder.userPositionTextView.setText(user.getPosition());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public static class UserViewHolder
            extends RecyclerView.ViewHolder {
        private final String TAG = this.getClass().getSimpleName();
        ImageView userAvatarImageView;
        TextView userNameTextView, userPositionTextView;


        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            Log.i(TAG, "UserViewHolder");
            userAvatarImageView = itemView.findViewById(R.id.userAvatarImageView);
            userNameTextView = itemView.findViewById(R.id.userNameTextView);
            userPositionTextView = itemView.findViewById(R.id.userPositionTextView);
        }
    }
}
