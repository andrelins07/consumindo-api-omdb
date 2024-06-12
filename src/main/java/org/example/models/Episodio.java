package org.example.models;
import java.util.OptionalDouble;

public class Episodio {

    private final Integer numero;
    private final String titulo;
    private Double avaliacao;
    private final Integer temporada;

    public Episodio(Integer temporada, DadosEpisodio e) {
        this.numero = e.numero();
        this.titulo = e.titulo();
        try {
            this.avaliacao = OptionalDouble.of(Double.parseDouble(e.avaliacao())).orElse(0);
        }catch (NumberFormatException exception){
            this.avaliacao = 0.0;
        }
        this.temporada = temporada;
    }
    public Double getAvaliacao() {
        return avaliacao;
    }

    public String getTitulo() {
        return titulo;
    }

    @Override
    public String toString() {
        return "Temporada=" + temporada +
                ", Ep="+ numero +
                ", Titulo=" + titulo +
                ", Avaliacao=" + String.format("%.2f", avaliacao);
    }
}
