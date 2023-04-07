package com.example.sigma.activity;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.sigma.R;
import com.example.sigma.adapter.UserRecyclerViewAdapter;
import com.example.sigma.database.DataBase;
import com.example.sigma.fragment.UserRowFragment;
import com.example.sigma.models.User;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private final String TAG = this.getClass().getSimpleName();

    private final static int maxPortfolioCount = 5;
    private static int userId = 0;
    private ArrayList<User> users;

    FragmentManager fragmentManager;

    private ActivityResultLauncher<Intent> mStartForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if(result.getResultCode() == RESULT_OK){
                    Log.i(TAG, "got result");
                    Intent intent = result.getData();
                    userId = (int)intent.getExtras().get("userId");
                }
                else{
                    Log.e(TAG, "Sign in or on error");
                }
                Log.i(TAG, "new userId" + userId);
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button seeAllButton = findViewById(R.id.seeAllButton);
        Button createNotificationButton = findViewById(R.id.createNotificationButton);

        seeAllButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, UserListActivity.class);
            startActivity(intent);
        });

        createNotificationButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, NotificationActivity.class);
            startActivity(intent);
        });

        fragmentManager = getSupportFragmentManager();

        users = DataBase.users;

        RecyclerView recyclerView = findViewById(R.id.userRecyclerView);
        UserRecyclerViewAdapter adapter = new UserRecyclerViewAdapter(this, users);
        LinearLayoutManager manager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        TextView textView = findViewById(R.id.myProfileButton);
        Log.i(TAG, "userId" + userId);
        if (userId == 0) {
            textView.setText(R.string.sign_in);
            textView.setOnClickListener(v -> {
                Log.i(TAG, "Sign in clicked");

                Intent intent = new Intent(this, SignInActivity.class);
                mStartForResult.launch(intent);
            });
            return;
        }
        textView.setText(R.string.my_profile_button_text);
        textView.setOnClickListener(v -> {
            Log.i(TAG, "My Profile clicked");
            Intent intent = new Intent(this, UserProfile.class);
            intent.putExtra("userId", userId);
            startActivity(intent);
        });
    }

    public static int getUserId(){
        return userId;
    }
}