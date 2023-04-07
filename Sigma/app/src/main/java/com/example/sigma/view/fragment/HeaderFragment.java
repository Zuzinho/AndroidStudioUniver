package com.example.sigma.view.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.sigma.view.activity.MainActivity;
import com.example.sigma.R;

public class HeaderFragment extends Fragment {
    private final String TAG = this.getClass().getSimpleName();

    public HeaderFragment(){
        super(R.layout.fragment_header);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_header, container, false);

        LinearLayout mainPageButton = view.findViewById(R.id.mainPageButton);

        mainPageButton.setOnClickListener(v -> {
            Log.i(TAG, "Clicked main page");

            Intent intent = new Intent(getContext(), MainActivity.class);
            startActivity(intent);
        });

        return view;
    }
}