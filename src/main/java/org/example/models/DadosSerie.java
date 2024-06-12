package org.example.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosSerie(
        @JsonProperty(value = "Title") String titulo,
        @JsonProperty(value = "Plot") String sinopse,
        @JsonProperty(value = "totalSeasons")Integer temporadas,
        @JsonProperty(value = "Genre") String genero,
        @JsonProperty(value = "imdbRating") String avaliacao,
        @JsonProperty(value = "Poster") String poster,
        @JsonProperty(value = "Year") String ano){

    @Override
    public String toString() {
        return "Serie = " + titulo + '\n' +
                "Sinopse = " + sinopse + '\n' +
                "Temporadas = " + temporadas +
                ", Genero = " + genero + '\'' +
                ", Avaliacao = " + avaliacao + '\n' +
                "Poster = " + poster + '\n';
    }
}
