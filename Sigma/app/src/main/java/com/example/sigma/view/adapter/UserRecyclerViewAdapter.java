package com.example.sigma.view.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sigma.R;
import com.example.sigma.model.User;

import java.util.ArrayList;

public class UserRecyclerViewAdapter extends
        RecyclerView.Adapter<UserRecyclerViewAdapter.UserViewHolder> {
    private final String TAG = this.getClass().getSimpleName();
    private LayoutInflater inflater;
    private ArrayList<User> users;

    public UserRecyclerViewAdapter(Context context, ArrayList<User> users){
        this.inflater = LayoutInflater.from(context);
        this.users = users;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.fragment_user_row, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = users.get(position);
        holder.userAvatarImageView.setImageResource(user.getAvatarPath());
        holder.userNameTextView.setText(user.getName());
        holder.userPositionTextView.setText(user.getPosition());
        holder.userRow.setOnClickListener(v -> {
            Log.i(TAG, "Clicked user`s portfolio");
            Toast.makeText(inflater.getContext(), "RecyclerView item clicked", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public static class UserViewHolder
            extends RecyclerView.ViewHolder {
        private final String TAG = this.getClass().getSimpleName();
        LinearLayout userRow;
        ImageView userAvatarImageView;
        TextView userNameTextView, userPositionTextView;


        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            userRow = itemView.findViewById(R.id.userRow);
            userAvatarImageView = itemView.findViewById(R.id.userAvatarImageView);
            userNameTextView = itemView.findViewById(R.id.userNameTextView);
            userPositionTextView = itemView.findViewById(R.id.userPositionTextView);
        }
    }
}
