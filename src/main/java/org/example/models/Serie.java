package org.example.models;

import java.util.*;

public class Serie {

    private final String titulo;
    private final String sinopse;
    private final Integer totalTemporadas;
    private final List<Temporada> temporadas = new ArrayList<>();
    private final String genero;
    private final Double avaliacao;
    private final String ano;

    public Serie(DadosSerie dados) {
        this.titulo = dados.titulo();
        this.sinopse = dados.sinopse();
        this.totalTemporadas = dados.temporadas();
        this.genero = dados.genero();
        this.avaliacao = OptionalDouble.of(Double.parseDouble(dados.avaliacao())).orElse(0);

        String[] anoSeparado = dados.ano().split("–");
        if(anoSeparado.length == 2){
           this.ano = dados.ano();
        }else{
            this.ano = anoSeparado[0] + "-atual";
        }
    }
    public String getAno() {
        return ano;
    }

    public String getTitulo() {
        return titulo;
    }

    public Integer getTotalTemporadas() {
        return totalTemporadas;
    }
    public void setTemporadas(Integer numero, List<Episodio> episodios) {
        temporadas.add(new Temporada(numero, episodios, episodios.size()));
    }

    public List<Temporada> getTemporadas() {
        return temporadas;
    }

    public String getSinopse() {
        return sinopse;
    }
    public Double getAvaliacao() {
        return avaliacao;
    }

    public String getGenero() {
        return genero;
    }

    @Override
    public String toString() {
        return "Serie{" +
                "titulo='" + titulo + '\'' +
                ", sinopse='" + sinopse + '\'' +
                ", temporadas=" + totalTemporadas +
                ", episodios=" + temporadas +
                ", genero='" + genero + '\'' +
                ", avaliacao=" + String.format("%.2f", avaliacao) +
                '}';
    }


}
