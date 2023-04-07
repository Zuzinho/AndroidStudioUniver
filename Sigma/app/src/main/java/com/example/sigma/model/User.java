package com.example.sigma.model;

public final class User {
    private final int id;
    private String name;
    private String position;
    private String info;
    private int AvatarPath;

    public User(int id, String name, String position, String info, int avatarPath) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.info = info;
        AvatarPath = avatarPath;
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
        return AvatarPath;
    }
}
