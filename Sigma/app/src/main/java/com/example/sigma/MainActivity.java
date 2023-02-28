package com.example.sigma;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
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
    private static int userId = 0;
    private ArrayList<User> users;

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

        TableLayout portfoliosTable = findViewById(R.id.portfoliosTable);

        users = DataBase.users;
        for(User user: users) portfoliosTable.addView(createPortfolioRow(user));
    }

    @Override
    protected void onResume() {
        super.onResume();

        TextView textView = findViewById(R.id.myProfileButton);
        Log.i(TAG, "userId" + userId);
        if(userId == 0) {
            textView.setText(R.string.sign_in);
            textView.setOnClickListener(v -> {
                Log.i(TAG, "Sign in clicked");

                Intent intent = new Intent(this, SignInActivity.class);
                mStartForResult.launch(intent);
            });
        }
        else{
            textView.setText(R.string.my_profile_button_text);
            textView.setOnClickListener(v -> {
                Log.i(TAG ,"My Profile clicked");
                Intent intent = new Intent(this, UserProfile.class);
                intent.putExtra("userId", userId);
                startActivity(intent);
            });
        }
    }

    public static int getUserId(){
        return userId;
    }

    @SuppressLint("ResourceAsColor")
    private LinearLayout createPortfolioRow(User user){
        Context context = getApplicationContext();

        LinearLayout portfolioRow = new LinearLayout(context);
        portfolioRow.setLayoutParams(new LinearLayout.LayoutParams(-2, -1));

        ImageView avatar = new ImageView(context);
        avatar.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        avatar.setImageResource(user.getAvatarPath());

        TableLayout userInfo = new TableLayout(context);
        LinearLayout.LayoutParams userInfoLayout = new LinearLayout.LayoutParams(-2, -1);
        userInfoLayout.setMarginStart(10);
        userInfo.setLayoutParams(userInfoLayout);
        userInfo.setOrientation(LinearLayout.VERTICAL);

        TextView userName = new TextView(context);
        userName.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        userName.setText(user.getName());
        userName.setTextSize(20);
        userName.setTypeface(userName.getTypeface(), Typeface.BOLD);

        TextView userPosition = new TextView(context);
        userPosition.setLayoutParams(new LinearLayout.LayoutParams(-2, -1));
        userPosition.setText(user.getPosition());
        userPosition.setTextSize(15);
        userPosition.setTextColor(R.color.gray);

        userInfo.addView(userName);
        userInfo.addView(userPosition);

        portfolioRow.addView(avatar);
        portfolioRow.addView(userInfo);

        portfolioRow.setOnClickListener(v -> {
            Log.i(TAG, "Clicked user`s portfolio");

            Intent intent = new Intent(this, UserProfile.class);
            int id = user.getId();
            intent.putExtra("userId", id);
            startActivity(intent);
        });

        return portfolioRow;
    }
}