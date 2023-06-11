package com.example.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Pokemon implements Serializable {
    private int id_pokemon;
    @SerializedName("id")
    private int id_api_pokemon;
    private int id_user;
    @SerializedName("name")
    private String name_pokemon;

    @SerializedName("sprites")
    private Sprites sprites;

    public Pokemon() {
        this.sprites = new Sprites();
    }

    public int getId_pokemon() {
        return id_pokemon;
    }

    public void setId_pokemon(int id_pokemon) {
        this.id_pokemon = id_pokemon;
    }

    public int getId_api_pokemon() {
        return id_api_pokemon;
    }

    public void setId_api_pokemon(int id_api_pokemon) {
        this.id_api_pokemon = id_api_pokemon;
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

    public Sprites getSprites() {
        return sprites;
    }

    public void setSprites(Sprites sprites) {
        this.sprites = sprites;
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "id_pokemon=" + id_pokemon +
                ", id_api_pokemon=" + id_api_pokemon +
                ", id_user=" + id_user +
                ", name_pokemon='" + name_pokemon + '\'' +
                ", URL = '" + sprites.getFrontDefault() + '\'';
    }
}
