package com.example.sigma.model.models;

import androidx.annotation.NonNull;

public final class User {
    private final int id;
    private String name;
    private String position;
    private String info;
    private int avatarPath;

    public User(int id, String name, String position, String info, int avatarPath) {
        this.id = id;
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

    @NonNull
    @Override
    public String toString() {
        return "Id: " + id + "; name: " + name + "; position: " + position + "; info: " + info + "; path: " + avatarPath;
    }
}
