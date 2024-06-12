package org.example.service;

import org.example.exception.MinhaException;
import org.example.models.*;

import java.util.*;
import java.util.stream.Collectors;

public class SerieService {
    private final Scanner leitura = new Scanner(System.in);
    private final ConsumoApi consumoApi = new ConsumoApi();
    private final JsonToObject toObject = new JsonToObject();
    private final MinhaException exception = new MinhaException();
    private final String API_KEY = "abef45c9";
    private Serie serie;

    public Serie buscarSerie() {

        System.out.println("Digite o nome da serie: ");
        String nomeSerie = leitura.nextLine();

        String endereco = "https://www.omdbapi.com/?t=" + nomeSerie.
                replace(" ", "+") + "&apikey=" + API_KEY;

        String json = consumoApi.obtendoDadosJson(endereco);

        if(!json.contains("totalSeasons")){
            throw new MinhaException("Serie não encontrada!");
        }
        DadosSerie dados = toObject.converterJson(json, DadosSerie.class);

        this.serie = new Serie(dados);

        return serie;
    }
    public void carregaEpisodios() {

        for (int i = 1; i <= serie.getTotalTemporadas(); i++) {

            String endereco = "https://www.omdbapi.com/?t=" + serie.getTitulo().
                    replace(" ", "+") + "&season=" + i + "&apikey=" + API_KEY;

            DadosTemporada dadosTemporada = toObject.converterJson(consumoApi
                    .obtendoDadosJson(endereco), DadosTemporada.class);

            Integer temporada = i;

            serie.setTemporadas(
                    temporada,
                    dadosTemporada
                            .episodios()
                            .stream()
                            .map(f -> new Episodio(temporada, f))
                            .collect(Collectors.toList()));
        }
    }
    public void detalharSerie() {

        List<Episodio> episodios = todosEpisodios();

        episodios = episodios.stream()
                .sorted(Comparator.comparing(Episodio::getAvaliacao).reversed()).toList();

        System.out.printf("""
                        Serie: %s
                        Sinopse: %s
                        Genero: %s
                        Ano: %s, Avaliação Geral: %.2f
                        Temporadas: %d, Total Episodios: %d
                        """, serie.getTitulo(), serie.getSinopse(), serie.getGenero(), serie.getAno(),
                serie.getAvaliacao(), serie.getTotalTemporadas(), episodios.size());

        System.out.println("Episódio melhor avaliado: " + episodios.get(0));
        System.out.println("Episódio pior avaliado: " + episodios.stream()
                .filter(e -> e.getAvaliacao() > 0)
                .min(Comparator.comparing(Episodio::getAvaliacao)).get());
        System.out.println("---------------------------------------------------------------");
    }

    public void buscarPorEpisodios() {

        System.out.println("Digite o nome do episodio: ");
        String nome = leitura.nextLine();

        List<Episodio> episodios = todosEpisodios();

        episodios.stream()
                .filter(e -> e.getTitulo().toLowerCase().contains(nome.toLowerCase()))
                .forEach(System.out::println);

    }
    public void exibirTemporadas() {

        for (int i = 0; i < serie.getTemporadas().size(); i++) {
            System.out.println(serie.getTemporadas().get(i));
        }
    }

    public void exibirEpisodios() {

        int opcao = exception.leitura("Digite a temporada: ", 0);
        serie.getTemporadas()
                .stream()
                .filter(t -> t.getNumero() == opcao)
                .forEach(t -> t.getEpisodios().forEach(System.out::println));
    }

    public void topCincoEpisodios() {

        List<Episodio> allEpisodios = todosEpisodios();


        allEpisodios.stream()
                .sorted(Comparator.comparing(Episodio::getAvaliacao).reversed())
                .limit(5)
                .forEach(System.out::println);
    }

    public List<Episodio> todosEpisodios() {

        List<Episodio> allEpisodios = new ArrayList<>();

        serie.getTemporadas().forEach(f -> allEpisodios.addAll(f.getEpisodios()));
        return Collections.unmodifiableList(allEpisodios);
    }

    public void imprimirTodasTemporadas() {

        for (Temporada temporada : serie.getTemporadas()) {
            temporada.getEpisodios()
                    .forEach(System.out::println);
        }
    }
}
