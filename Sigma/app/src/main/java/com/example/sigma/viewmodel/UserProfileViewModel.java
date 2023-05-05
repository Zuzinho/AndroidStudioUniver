package com.example.sigma.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sigma.R;
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
    }

}
