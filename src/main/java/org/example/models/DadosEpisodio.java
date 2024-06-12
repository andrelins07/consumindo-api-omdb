package org.example.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosEpisodio(
        @JsonProperty("Episode") Integer numero,
        @JsonProperty("Title") String titulo,
        @JsonProperty("imdbRating") String avaliacao) {}
