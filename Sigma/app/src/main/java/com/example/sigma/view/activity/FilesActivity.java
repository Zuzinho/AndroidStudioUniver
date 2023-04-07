package com.example.sigma.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.example.sigma.R;
import com.example.sigma.databinding.ActivityFilesBinding;
import com.example.sigma.view.fragment.HeaderFragment;
import com.example.sigma.viewmodel.FilesActivityViewModel;

import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class FilesActivity extends AppCompatActivity {
    private final String TAG = this.getClass().getSimpleName();
    FragmentManager fragmentManager;
    private FilesActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_files);
        viewModel = new ViewModelProvider(this).get(FilesActivityViewModel.class);
        viewModel.initViewModel(this);

        if(savedInstanceState == null){
            fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().setReorderingAllowed(true).
                    add(R.id.headerContainerView, HeaderFragment.class, null).commit();
        }

        Button appSpecificStorageButton = findViewById(R.id.appSpecificStorageButton);
        Button sharedStorageButton = findViewById(R.id.sharedStorageButton);
        Button sharedPreferencesButton = findViewById(R.id.sharedPreferencesButton);

        Button insertUserButton = findViewById(R.id.insertUserButton);
        Button getUserButton = findViewById(R.id.getUserButton);

        appSpecificStorageButton.setOnClickListener(v -> {
            try {
                Log.i(TAG,  viewModel.addFileAppSpecificStorage());
            } catch (IOException e) {
                Log.i(TAG, e.getMessage());
            }
        });

        sharedStorageButton.setOnClickListener(v -> {
            try {
                Log.i(TAG,  viewModel.addSharedStorage());
            } catch (IOException e) {
                Log.i(TAG, e.getMessage());
            }
        });

        sharedPreferencesButton.setOnClickListener(v -> Log.i(TAG, viewModel.addSharedPreferences()));


        insertUserButton.setOnClickListener(v -> {
            Executor executor = Executors.newSingleThreadExecutor();
            executor.execute(()->Log.i(TAG, viewModel.addDataBase()));
        });

        getUserButton.setOnClickListener(v -> {
            Executor executor = Executors.newSingleThreadExecutor();
            executor.execute(()->Log.i(TAG, viewModel.getDataBase()));
        });
    }
}