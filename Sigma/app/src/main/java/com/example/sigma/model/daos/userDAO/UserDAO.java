package com.example.sigma.model.daos.userDAO;

import com.example.sigma.model.models.User;

public interface UserDAO {
    public User get(int userId);
    public void create(User user);
    public void update(User user);
    public void delete(User user);
}
