package com.example.sigma;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.sigma.databinding.FragmentHeaderBinding;

public class HeaderFragment extends Fragment {
    private final String TAG = this.getClass().getSimpleName();

    public HeaderFragment(){
        super(R.layout.fragment_header);
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        Log.i(TAG, "onCreate");
        Toast.makeText(getContext(), "onCreate", Toast.LENGTH_SHORT).show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_header, container, false);

        Log.i(TAG, "onCreateView");
        Toast.makeText(getContext(), "onCreateView", Toast.LENGTH_SHORT).show();

        LinearLayout mainPageButton = view.findViewById(R.id.mainPageButton);

        mainPageButton.setOnClickListener(v -> {
            Log.i(TAG, "Clicked main page");

            Intent intent = new Intent(getContext(), MainActivity.class);
            startActivity(intent);
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        Log.i(TAG, "onStart");
        Toast.makeText(getContext(), "onStart", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResume() {
        super.onResume();

        Log.i(TAG, "onResume");
        Toast.makeText(getContext(), "onResume", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPause() {
        super.onPause();

        Log.i(TAG, "onPause");
        Toast.makeText(getContext(), "onPause", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStop() {
        super.onStop();

        Log.i(TAG, "onStop");
        Toast.makeText(getContext(), "onStop", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        Log.i(TAG, "onDestroyView");
        Toast.makeText(getContext(), "onDestroyView", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.i(TAG, "onDestroy");
        Toast.makeText(getContext(), "onDestroy", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDetach() {
        super.onDetach();

        Log.i(TAG, "onDetach");
        Toast.makeText(getContext(), "onDetach", Toast.LENGTH_SHORT).show();
    }
}