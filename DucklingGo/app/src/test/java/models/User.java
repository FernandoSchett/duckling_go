package models;

import java.io.Serializable;

public class User implements Serializable {
    private int id_user;
    private String username;
    private String email;
    private String password;
    private String DT_last_hatch;

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDT_last_hatch() {
        return DT_last_hatch;
    }

    public void setDT_last_hatch(String DT_last_hatch) {
        this.DT_last_hatch = DT_last_hatch;
    }
}
