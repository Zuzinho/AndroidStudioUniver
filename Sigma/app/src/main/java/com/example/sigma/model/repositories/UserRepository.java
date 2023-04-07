package com.example.sigma.model.repositories;

import com.example.sigma.model.daos.userDAO.factory.UserDAOFactory;
import com.example.sigma.model.models.User;

public class UserRepository {
    public User get(UserDAOFactory factory, int userId){
        return factory.get().get(userId);
    }

    public void create(UserDAOFactory factory, User user){
        factory.get().create(user);
    }

    public void update(UserDAOFactory factory, User user){
        factory.get().update(user);
    }

    public void delete(UserDAOFactory factory, User user){
        factory.get().delete(user);
    }
}
