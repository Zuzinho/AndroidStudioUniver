package com.example.sigma.database;

import com.example.sigma.R;
import com.example.sigma.models.User;

import java.util.ArrayList;

public class DataBase {
    public final static ArrayList<User> users = getUsers(200);

    private static ArrayList<User> getUsers(int count){
        ArrayList<User> arrayList = new ArrayList<>(count);
        for(int i = 1;i < count + 1; i++)  arrayList.add(new User(i, "Name" + i, "Position" + i, "Info" + i, R.mipmap.user_avatar0));
        return arrayList;
    }

    public static User getUserById(int userId){
        for(User user: users){
            if(user.getId() == userId) return user;
        }
        return null;
    }
}
