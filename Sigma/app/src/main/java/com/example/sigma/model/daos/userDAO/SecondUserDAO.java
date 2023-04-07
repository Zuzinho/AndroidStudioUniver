package com.example.sigma.model.daos.userDAO;

import com.example.sigma.model.database.DataBase;
import com.example.sigma.model.models.User;

import java.util.List;

public class SecondUserDAO implements UserDAO{
    private List<User> users = DataBase.users;
    @Override
    public User get(int userId) {
        for(User user: users){
            if(user.getId() == userId) return user;
        }
        return null;
    }

    @Override
    public void create(User user) {
        users.add(user);
    }

    @Override
    public void update(User user) {
        int userId = user.getId();
        for(int i = 0; i< users.size(); i++){
            if(users.get(i).getId() == userId){
                users.set(i, user);
                return;
            }
        }
    }

    @Override
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

