package com.example.sigma.model.repositories;

import com.example.sigma.model.daos.userDAO.FirstUserDAO;
import com.example.sigma.model.daos.userDAO.SecondUserDAO;
import com.example.sigma.model.models.User;

public class UserRepository {
    FirstUserDAO firstUserDAO = new FirstUserDAO();
    SecondUserDAO secondUserDAO = new SecondUserDAO();

    public User get(int userId){
        return firstUserDAO.get(userId);
    }

    public void create(User user){
        secondUserDAO.create(user);
    }

    public void update(User user){
        firstUserDAO.update(user);
    }

    public void delete(User user){
        secondUserDAO.delete(user);
    }
}
