package org.example.models;

import java.util.List;
import java.util.stream.Collectors;

public class Temporada {
    private final Integer numero;
    private final List<Episodio> episodios;
    private final Integer totalEpisodios;
    private final Double avaliacao;

    public Temporada(Integer numero, List<Episodio> episodios, Integer size) {
        this.numero = numero;
        this.episodios = episodios;
        this.totalEpisodios = size;

        this.avaliacao = episodios.stream()
                .filter(e -> e.getAvaliacao() > 0.0)
                .collect(Collectors.averagingDouble(Episodio::getAvaliacao));
    }

    public Integer getNumero() {
        return numero;
    }

    public List<Episodio> getEpisodios() {
        return episodios;
    }

    @Override
    public String toString() {
        return "Temporada " + numero +
                " --> Total de Episodios = " + totalEpisodios +
                ", Avaliacao = " + String.format("%.2f", avaliacao);
    }
}
