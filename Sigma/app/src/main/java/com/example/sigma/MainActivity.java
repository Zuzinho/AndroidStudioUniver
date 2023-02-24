package com.example.sigma;

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

import java.lang.reflect.Array;
import java.text.AttributedCharacterIterator;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private final String TAG = this.getClass().getSimpleName();
    private static int userId = 0;
    private ArrayList<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TableLayout portfoliosTable = (TableLayout)findViewById(R.id.portfoliosTable);

        users = DataBase.users;
        for(User user: users) portfoliosTable.addView(createPortfolioRow(user));
    }

    public static void setUserId(int newUserId){
        userId = newUserId;
    }

    public void onMyProfileClick(View v){
        Log.i(TAG, "Clicked My Profile");
        Intent intent;
        if(userId == 0) {
            intent = new Intent(this, SignInActivity.class);
        }
        else {
            intent = new Intent(this, UserProfile.class);
            intent.putExtra("id", userId);
        }
        startActivity(intent);
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
            intent.putExtra("id", user.getId());
            startActivity(intent);
        });

        return portfolioRow;
    }
}