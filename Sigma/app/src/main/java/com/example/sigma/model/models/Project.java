package com.example.sigma.model.models;

public class Project {
    private final int id;
    private String title;
    private String photoUrl;
    private String about;
    private int userId;
    private String technology;
    private boolean selected;

    public Project(int id, String title, String photoUrl, String about, int userId, String technology, boolean selected) {
        this.id = id;
        this.title = title;
        this.photoUrl = photoUrl;
        this.about = about;
        this.userId = userId;
        this.technology = technology;
        this.selected = selected;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public String getAbout() {
        return about;
    }

    public int getUserId() {
        return userId;
    }

    public String getTechnology() {
        return technology;
    }

    public boolean isSelected() {
        return selected;
    }
}
