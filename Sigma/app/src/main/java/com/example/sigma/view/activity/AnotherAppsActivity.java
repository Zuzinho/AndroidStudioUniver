package com.example.sigma.view.activity;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sigma.R;
import com.example.sigma.view.fragment.HeaderFragment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class AnotherAppsActivity extends AppCompatActivity {
    private final String TAG = this.getClass().getSimpleName();

    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another_apps);

        if(savedInstanceState == null){
            fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().setReorderingAllowed(true).
                    add(R.id.headerContainerView ,HeaderFragment.class, null).commit();
        }

        Button startAnotherApp = findViewById(R.id.startAnotherApp);
        Button getAnotherAppButton = findViewById(R.id.getAnotherAppButton);

        EditText telEditText = findViewById(R.id.telEditText);

        startAnotherApp.setOnClickListener(v -> {
            String telNumber = telEditText.getText().toString();
            Uri number = Uri.parse("tel:" + telNumber);
            Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
            startActivity(callIntent);
            
        });


    }
}