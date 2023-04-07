package com.example.sigma.viewmodel;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Path;
import android.os.Environment;
import android.util.Log;

import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModel;

import com.example.sigma.R;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

public class FilesActivityViewModel extends ViewModel {
    private final String TAG = this.getClass().getSimpleName();
    private Context context;

    public void initViewModel(Context context){
        this.context = context;
    }

    public String addFileAppSpecificStorage() throws IOException {
        File file = new File(context.getFilesDir(), "file1.txt");
        return readFile(file);
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
