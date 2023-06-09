package com.example.sessions;

public class PokemonSession {
    private String url_img_pokemon;
    private String pokemon_name;
    private int id_pokemon;

    private static PokemonSession instance;

    private PokemonSession() {
        // Construtor privado para evitar criação de instâncias adicionais
    }

    public static PokemonSession getInstance() {
        if (instance == null) {
            instance = new PokemonSession();
        }
        return instance;
    }

    public void setPokemonData(String url_img_pokemon, String pokemon_name, int id_pokemon) {
        this.url_img_pokemon = url_img_pokemon;
        this.pokemon_name = pokemon_name;
        this.id_pokemon = id_pokemon;
    }

    public String getUrl() {
        return url_img_pokemon;
    }

    public String getName() {
        return pokemon_name;
    }

    public int getId() {
        return id_pokemon;
    }
}
