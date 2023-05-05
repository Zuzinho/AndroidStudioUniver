package com.example.sigma;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.sigma.databinding.ActivityUserProfileBinding;

public class UserRowWorker extends Worker {
    private final String TAG = this.getClass().getSimpleName();
    private static Context context;
    public UserRowWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        Data data = getInputData();

        Log.i(TAG, "" + data.getInt(context.getString(R.string.user_avatar), 0));
        Log.i(TAG, data.getString(context.getString(R.string.user_name)));
        Log.i(TAG, data.getString(context.getString(R.string.user_position)));
        Log.i(TAG, data.getString(context.getString(R.string.user_info)));

        return Result.success();
    }

    public static void setContext(Context newContext){
        context = newContext;
    }
}
