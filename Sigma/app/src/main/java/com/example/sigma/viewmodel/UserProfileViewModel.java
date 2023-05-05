package com.example.sigma.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import com.example.sigma.R;
import com.example.sigma.UserRowWorker;
import com.example.sigma.databinding.ActivityUserProfileBinding;
import com.example.sigma.model.models.User;
import com.example.sigma.model.repositories.UserRepository;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class UserProfileViewModel extends ViewModel {
    private final String TAG = this.getClass().getSimpleName();
    private Context context;
    private MutableLiveData<User> user;
    private ActivityUserProfileBinding view;

    private ExecutorService executorService;
    private WorkManager workManager;

    private Runnable setUserAvatar = () -> {
        Log.i(TAG, "setUserAvatar");
        view.userAvatar.setImageResource(user.getValue().getAvatarPath());
    };
    private Runnable setUserName = () -> {
        Log.i(TAG, "setUserName");
        view.userName.setText(user.getValue().getName());
    };
    private Runnable setUserPosition = () -> {
        Log.i(TAG, "setUserPosition");
        view.userPosition.setText(user.getValue().getPosition());
    };
    private Runnable setUserInfo = () -> {
        Log.i(TAG, "setUserInfo");
        view.userInfo.setText(user.getValue().getInfo());
    };

    public void initViewModel(Context context, Intent intent, ActivityUserProfileBinding view){
        this.context = context;
        int userId = intent.getExtras().getInt(context.getString(R.string.user_id));
        this.user = new MutableLiveData<>(new UserRepository().get(userId));
        this.view = view;

        this.executorService = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingDeque<Runnable>());

        workManager = WorkManager.getInstance(context);
        UserRowWorker.setContext(context);
    }

    public boolean existUser(){
        return user != null;
    }

    public MutableLiveData<User> getUser(){
        return this.user;
    }

    public void setView(User userValue){
        executorService.execute(setUserAvatar);
        executorService.execute(setUserName);
        executorService.execute(setUserPosition);
        executorService.execute(setUserInfo);
        executorService.shutdown();

        OneTimeWorkRequest userProfileRequest =
                new OneTimeWorkRequest.Builder(UserRowWorker.class).
                        setInputData(createUserRowData(userValue)).build();

        workManager.enqueue(userProfileRequest);
    }

    private Data createUserRowData(User userValue){
        Data.Builder builder = new Data.Builder();
        builder.putInt(context.getString(R.string.user_avatar), userValue.getAvatarPath());
        builder.putString(context.getString(R.string.user_name), userValue.getName());
        builder.putString(context.getString(R.string.user_position), userValue.getPosition());
        builder.putString(context.getString(R.string.user_info), userValue.getInfo());

        return builder.build();
    }

}
