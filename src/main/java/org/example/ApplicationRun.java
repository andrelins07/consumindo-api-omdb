package org.example;

import org.example.exception.MinhaException;
import org.example.models.Serie;
import org.example.service.SerieService;

public class ApplicationRun {
    private static final MinhaException exception = new MinhaException();

    public static void main(String[] args) {

        Serie serie;

        SerieService service = new SerieService();

        int opcao = menuInicial();

        while (opcao == 1) {

            try {
                serie = service.buscarSerie();
                System.out.println("SERIE SELECIONADA: " + serie.getTitulo());
                service.carregaEpisodios();

                int opcao2 = exibirMenu();
                System.out.println();

                while (opcao2 != 0) {
                    switch (opcao2) {
                        case 1 -> service.detalharSerie();
                        case 2 -> service.exibirTemporadas();
                        case 3 -> service.exibirEpisodios();
                        case 4 -> service.buscarPorEpisodios();
                        case 5 -> service.topCincoEpisodios();
                        case 6 -> service.imprimirTodasTemporadas();
                        default -> System.out.println("OPCÃO INVALIDA!");
                    }
                    opcao2 = exibirMenu();
                }
            } catch (MinhaException e) {
                System.out.println(e.getMessage());
            }
            opcao = menuInicial();
        }
        System.out.println("FINALIZANDO...");
    }

    public static int menuInicial() {
        String menu = """  
                1 - Buscar Serie
                (Digite qualquer número para SAIR)
                """;
        return exception.leitura(menu, 0);
    }

    public static int exibirMenu() {

        String menu = """
                1 - Descrição geral da série
                2 - Exibir temporadas
                3 - Exibir episodios de uma temporada
                4 - Buscar por um episódio
                5 - Top 5 episodios
                6 - Exibir todos os episódios
                0 - Voltar
                """;
        return exception.leitura(menu, 0);
    }

}
