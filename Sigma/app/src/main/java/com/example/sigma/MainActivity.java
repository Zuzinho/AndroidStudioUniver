package com.example.sigma;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.sigma.database.DataBase;
import com.example.sigma.models.User;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.text.AttributedCharacterIterator;
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
        seeAllButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, UserListActivity.class);
            startActivity(intent);
        });

        fragmentManager = getSupportFragmentManager();

        users = DataBase.users;

        TableLayout portfoliosTable = findViewById(R.id.portfoliosTable);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -1);

        for(int i = 0;i < users.size();i++) {
            if(i == maxPortfolioCount) return;
            User user = users.get(i);
            FragmentContainerView containerView = new FragmentContainerView(getApplicationContext());
            containerView.setLayoutParams(layoutParams);
            containerView.setId(user.getId());
            Bundle bundle = new Bundle();
            bundle.putInt("userId", user.getId());
            bundle.putString("userName", user.getName());
            bundle.putString("userPosition", user.getPosition());
            bundle.putInt("userAvatar", user.getAvatarPath());
            bundle.putString("userInfo", user.getInfo());
            fragmentManager.beginTransaction().setReorderingAllowed(true).
                    add(user.getId(), UserRowFragment.class, bundle).commit();
            portfoliosTable.addView(containerView);
        }
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