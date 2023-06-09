package com.example.sessions;

public class UserSession {
    private static UserSession instance;
    private int userId;
    private String username;

    private UserSession() {
        // Construtor privado para evitar instâncias externas
    }

    public static synchronized UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void clearSession() {
        userId = 0;
        username = null;
    }
}
