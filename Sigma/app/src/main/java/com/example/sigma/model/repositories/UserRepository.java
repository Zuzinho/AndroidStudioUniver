package com.example.sigma.model.repositories;

import com.example.sigma.model.daos.userDAO.FirstDataSource;
import com.example.sigma.model.daos.userDAO.SecondDataSource;
import com.example.sigma.model.models.User;

public class UserRepository {
    FirstDataSource firstUserDAO = new FirstDataSource();
    SecondDataSource secondDataSource = new SecondDataSource();

    public User get(int userId){
        return firstUserDAO.get(userId);
    }

    public void create(User user){
        secondDataSource.create(user);
    }

    public void update(User user){
        firstUserDAO.update(user);
    }

    public void delete(User user){
        secondDataSource.delete(user);
    }
}
