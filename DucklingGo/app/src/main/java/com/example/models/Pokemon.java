package com.example.models;

import java.io.Serializable;

public class Pokemon implements Serializable {
    private int id_pokemon;
    private int id;
    private int id_user;
    private String name_pokemon;
    private String URL_img_pokemon;

    public int getId_pokemon() {
        return id_pokemon;
    }

    public void setId_pokemon(int id_pokemon) {
        this.id_pokemon = id_pokemon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getName_pokemon() {
        return name_pokemon;
    }

    public void setName_pokemon(String name_pokemon) {
        this.name_pokemon = name_pokemon;
    }

    public String getURL_img_pokemon() {
        return URL_img_pokemon;
    }

    public void setURL_img_pokemon(String URL_img_pokemon) {
        this.URL_img_pokemon = URL_img_pokemon;
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "id_pokemon=" + id_pokemon +
                ", id_api_pokemon=" + id +
                ", id_user=" + id_user +
                ", name_pokemon='" + name_pokemon + '\'' +
                ", URL_img_pokemon='" + URL_img_pokemon + '\'' +
                '}';
    }
}
