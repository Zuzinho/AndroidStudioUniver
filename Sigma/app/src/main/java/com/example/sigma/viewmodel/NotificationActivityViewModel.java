package com.example.sigma.viewmodel;

import android.Manifest;
import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.util.Log;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.lifecycle.ViewModel;

import com.example.sigma.R;
import com.example.sigma.model.database.DataBase;
import com.example.sigma.view.activity.NotificationActivity;

public class NotificationActivityViewModel extends ViewModel {
    private Context context;
    private final String CHANNEL_ID = "My notifications";


    public void initViewModel(Context context){
        this.context = context;
    }

    public void showNotification(String text) {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID).
                    setSmallIcon(R.mipmap.logo_image_round).
                    setContentTitle("Your notification").
                    setAutoCancel(true).
                    setPriority(NotificationCompat.PRIORITY_DEFAULT);

            if (text.isEmpty()) {
                builder.setContentText("Enter text in entry line");
            } else {
                builder.setContentText(text);
            }

            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

            notificationManager.notify(1, builder.build());
        }
    }

    public void createChannel(){
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
            NotificationManagerCompat.from(context).createNotificationChannel(mChannel);
        }
    }

    public void requestPermissions(int requestCode){
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions((Activity) context,
                    new String[]{
                            Manifest.permission.POST_NOTIFICATIONS
                    },
                    requestCode);
        }
    }
}
