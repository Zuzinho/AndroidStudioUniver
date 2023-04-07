package com.example.sigma.model.daos.userDAO.factory;

import com.example.sigma.model.daos.userDAO.FirstUserDAO;
import com.example.sigma.model.daos.userDAO.UserDAO;

public class FirstUserUserDAOFactory implements UserDAOFactory {
    @Override
    public UserDAO get() {
        return new FirstUserDAO();
    }
}
