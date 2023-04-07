package com.example.sigma.view.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.example.sigma.view.service.MyService;
import com.example.sigma.R;
import com.example.sigma.view.fragment.HeaderFragment;
import com.example.sigma.viewmodel.NotificationActivityViewModel;

public class NotificationActivity extends AppCompatActivity {
    private final String TAG = this.getClass().getSimpleName();
    FragmentManager fragmentManager;
    private NotificationActivityViewModel viewModel;

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        viewModel = new ViewModelProvider(this).get(NotificationActivityViewModel.class);
        viewModel.initViewModel(this);

        if (savedInstanceState == null) {
            fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().setReorderingAllowed(true).
                    add(R.id.headerContainerView, HeaderFragment.class, null).commit();
        }

        viewModel.createChannel();

        viewModel.requestPermissions(1);

        EditText notificationEditText = findViewById(R.id.notificationEditText);
        Button notificationButton = findViewById(R.id.notificationButton);
        Button serviceButton = findViewById(R.id.serviceButton);

        notificationButton.setOnClickListener(v -> viewModel.showNotification(notificationEditText.getText().toString()));

        serviceButton.setOnClickListener(v -> startService(new Intent(this, MyService.class)));
    }


}