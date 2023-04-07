package com.example.sigma.view.activity;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.sigma.R;
import com.example.sigma.viewmodel.MainActivityViewModel;

public class MainActivity extends AppCompatActivity {
    private final String TAG = this.getClass().getSimpleName();
    FragmentManager fragmentManager;
    private MainActivityViewModel viewModel;

    private final ActivityResultLauncher<Intent> mStartForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            result -> {
                int userId = result.getData().getExtras().getInt("userId");
                viewModel.initViewModel(this, userId);
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        viewModel.initViewModel(this, 0);

        Button seeAllButton = findViewById(R.id.seeAllButton);
        Button createNotificationButton = findViewById(R.id.createNotificationButton);

        seeAllButton.setOnClickListener(v -> startActivity(new Intent(this, UserListActivity.class)));

        createNotificationButton.setOnClickListener(v -> startActivity(new Intent(this, NotificationActivity.class)));

        fragmentManager = getSupportFragmentManager();

        RecyclerView recyclerView = findViewById(R.id.userRecyclerView);
        viewModel.setRecyclerView(recyclerView);
    }

    @Override
    protected void onResume() {
        super.onResume();

        TextView textView = findViewById(R.id.myProfileButton);
        Intent viewModelIntent = viewModel.getIntent();
        String signInContent = viewModelIntent.getStringExtra("signInContent");
        textView.setText(signInContent);
        boolean signedIn = viewModelIntent.getExtras().getBoolean("signedIn");
        if(!signedIn){
            textView.setOnClickListener(v -> mStartForResult.launch(new Intent(this, SignInActivity.class)));

            return;
        }

        int userId = viewModelIntent.getExtras().getInt("userId");
        textView.setOnClickListener(v -> {Intent intent = new Intent(this, UserProfile.class);
            intent.putExtra("userId", userId);
            startActivity(intent);
        });
    }
}