package com.example.sigma.activity;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.example.sigma.R;
import com.example.sigma.fragment.HeaderFragment;

import java.security.Permission;

public class NotificationActivity extends AppCompatActivity {
    private final String TAG = this.getClass().getSimpleName();
    private final String CHANNEL_ID = "My notifications";
    FragmentManager fragmentManager;

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        if (savedInstanceState == null) {
            fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().setReorderingAllowed(true).
                    add(R.id.headerContainerView, HeaderFragment.class, null).commit();
        }

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            CharSequence name = "My channel";
            String Description = "This is my channel";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
            mChannel.setDescription(Description);
            mChannel.enableLights(true);
            mChannel.setLightColor(Color.RED);
            mChannel.enableVibration(true);
            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            mChannel.setShowBadge(false);
            NotificationManagerCompat.from(this).createNotificationChannel(mChannel);
        }

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{
                            Manifest.permission.POST_NOTIFICATIONS
                    },
                    1);
        }


        EditText notificationEditText = findViewById(R.id.notificationEditText);
        Button notificationButton = findViewById(R.id.notificationButton);

        notificationButton.setOnClickListener(v -> showNotification(notificationEditText.getText().toString()));

    }

    private void showNotification(String text) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
            Log.i(TAG, "create builder");
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID).
                    setSmallIcon(R.mipmap.logo_image_round).
                    setContentTitle("Your notification").
                    setAutoCancel(true).
                    setPriority(NotificationCompat.PRIORITY_DEFAULT);

            if (text.isEmpty()) {
                builder.setContentText("Enter text in entry line");
            } else {
                builder.setContentText(text);
            }

            Log.i(TAG, "create NotificationManagerCompat");
            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

            Log.i(TAG, "notify NotificationManagerCompat");
            notificationManager.notify(1, builder.build());
        }
    }
}