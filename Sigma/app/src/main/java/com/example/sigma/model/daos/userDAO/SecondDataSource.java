package com.example.sigma.model.daos.userDAO;

import com.example.sigma.model.database.DataBase;
import com.example.sigma.model.models.User;

import java.util.List;

public class SecondDataSource {
    private List<User> users = DataBase.users;
    public User get(int userId) {
        for(User user: users){
            if(user.getId() == userId) return user;
        }
        return null;
    }

    public void create(User user) {
        users.add(user);
    }

    public void update(User user) {
        int userId = user.getId();
        for(int i = 0; i< users.size(); i++){
            if(users.get(i).getId() == userId){
                users.set(i, user);
                return;
            }
        }
    }
    public void delete(User user) {
        int userId = user.getId();
        for(int i = 0; i< users.size(); i++){
            if(users.get(i).getId() == userId){
                users.remove(i);
                return;
            }
        }
    }
}

