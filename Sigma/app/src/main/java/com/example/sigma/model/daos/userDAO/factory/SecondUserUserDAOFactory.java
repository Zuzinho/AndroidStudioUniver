package com.example.sigma.model.daos.userDAO.factory;

import com.example.sigma.model.daos.userDAO.SecondUserDAO;
import com.example.sigma.model.daos.userDAO.UserDAO;

public class SecondUserUserDAOFactory implements UserDAOFactory {
    @Override
    public UserDAO get() {
        return new SecondUserDAO();
    }
}
