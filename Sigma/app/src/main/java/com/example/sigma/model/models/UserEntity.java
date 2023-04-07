package com.example.sigma.model.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_table")
public class UserEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "position")
    private String position;

    @ColumnInfo(name = "info")
    private String info;

    @ColumnInfo(name = "avatar_path")
    private int avatarPath;

    public UserEntity(String name, String position, String info, int avatarPath){
        this.name = name;
        this.position = position;
        this.info = info;
        this.avatarPath = avatarPath;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public String getInfo() {
        return info;
    }

    public int getAvatarPath() {
        return avatarPath;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setAvatarPath(int avatarPath) {
        this.avatarPath = avatarPath;
    }
}
