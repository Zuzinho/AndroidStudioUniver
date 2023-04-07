package com.example.sigma.viewmodel;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Environment;

import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModel;
import androidx.room.Room;

import com.example.sigma.R;
import com.example.sigma.model.database.AppData;
import com.example.sigma.model.database.DBUserDAO;
import com.example.sigma.model.database.DataBase;
import com.example.sigma.model.models.User;
import com.example.sigma.model.models.UserEntity;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class FilesActivityViewModel extends ViewModel {
    private final String TAG = this.getClass().getSimpleName();
    private List<User> users = DataBase.users;
    private Context context;
    AppData db;

    public void initViewModel(Context context){
        this.context = context;
        db = Room.databaseBuilder(context.getApplicationContext(),
                AppData.class, "DataBase1").build();

    }

    public String addFileAppSpecificStorage() throws IOException {
        File file = new File(context.getFilesDir(), "file1.txt");
        return readFile(file);
    }

    public String addSharedStorage() throws IOException {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {

            ActivityCompat.requestPermissions((Activity) context,
                new String[]{
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                },
                1);
        }
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {

            ActivityCompat.requestPermissions((Activity) context,
                    new String[]{
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                    },
                    1);
        }

        File file = new File(context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "file2.txt");
        return readFile(file);
    }

    public String addSharedPreferences(){
        SharedPreferences sharedPreferences = ((Activity)context).getPreferences(Context.MODE_PRIVATE);
        return sharedPreferences.getString("content", "Error");
    }

    public String addDataBase(){
        DBUserDAO userDAO = db.userDAO();
        UserEntity user = new UserEntity("Nick", "Nicks pos", "Nicks info", R.mipmap.user_avatar0_round);
        userDAO.insert(user);

        return "Inserted";
    }

    public String getDataBase(){
        DBUserDAO userDAO = db.userDAO();
        UserEntity user = userDAO.getAll().get(0);
        return user.toString();
    }

    private String readFile(File file){
        try(FileReader fileReader = new FileReader(file)){
            int s;
            StringBuilder line = new StringBuilder();
            while((s = fileReader.read()) != -1){
                line.append((char) s);
            }
            return line.toString();
        }
        catch (Exception e){
            return e.getMessage();
        }
    }
}
