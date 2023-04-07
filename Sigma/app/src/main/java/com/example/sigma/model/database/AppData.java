package com.example.sigma.model.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.sigma.model.daos.userDAO.UserDAO;
import com.example.sigma.model.models.UserEntity;

@Database(entities = {UserEntity.class}, version = 1)
public abstract class AppData extends RoomDatabase {
    public abstract DBUserDAO userDAO();
}
