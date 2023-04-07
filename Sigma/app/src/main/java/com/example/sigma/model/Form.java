package com.example.sigma.model;

public class Form {
    private final int id;
    private String email;
    private String password;
    private int recoverCode;

    public Form(int id, String email, String password, int recoverCode) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.recoverCode = recoverCode;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getRecoverCode() {
        return recoverCode;
    }
}
