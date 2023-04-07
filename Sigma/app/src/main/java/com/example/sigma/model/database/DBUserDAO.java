package com.example.sigma.model.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.sigma.model.models.User;
import com.example.sigma.model.models.UserEntity;

import java.util.List;

@Dao
public interface DBUserDAO {
    @Insert
    void insert(UserEntity user);

    @Update
    void update(UserEntity user);

    @Delete
    void delete(UserEntity user);

    @Query("SELECT * FROM user_table")
    List<UserEntity> getAll();
}
