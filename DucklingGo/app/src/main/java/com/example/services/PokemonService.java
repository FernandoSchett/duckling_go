package com.example.services;

import com.example.models.Pokemon;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PokemonService {
    @GET("pokemon/{id}")
    Call<Pokemon> getPokemon(@Path("id") int pokemonId);
}
