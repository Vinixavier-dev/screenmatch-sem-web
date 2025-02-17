package br.com.alura.screenmatch2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.com.alura.model.DadosEpisodio;
import br.com.alura.model.DadosSerie;
import br.com.alura.model.DadosTemporada;
import br.com.alura.service.ConsumirAPI;
import br.com.alura.service.ConverteDados;

public class Principal {

    //declarações de atributos componentes para formar a url da API.
    //estão como final pois não podem ser alterados. são como constantes.
    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=176e2e95";
    private final String SEASON = "&season=";

    //metodo que pergunta o nome da série 
    public void exebirMenu() {
        var consumoapi = new ConsumirAPI();
        var converteDados = new ConverteDados();
        Scanner scanner = new Scanner(System.in);

        //pergunta e grava a série inserida pelo usuário em uma string.
        System.out.println("Escreva uma série: ");
        String serie = scanner.nextLine();

        //joga o retorno do método obterDados dentro de uma var (ou string) json, ou seja o próprio json da série.
        //depois descerializa esse json (json -> objeto java) dentro de uma instancia da record DadosSerie.
        var json = consumoapi.obterDados(ENDERECO + serie.replace(" ", "+") + API_KEY);
        DadosSerie dadosSerie = converteDados.obterDados(json, DadosSerie.class);
        System.out.println(dadosSerie);

        //cria uma lista do tipo record DadosTemporada.
        List<DadosTemporada> listaTemporadas = new ArrayList<>();

        //iteração por cada temporada, adicionando cada desceriaização do json dentro de uma record de temporada em uma lista.
        //printra cada item dentro da lista de temporada.
        for (int i = 1; i <= dadosSerie.totalTemporadas(); i++) {
            json = consumoapi.obterDados(ENDERECO + serie.replace(" ", "+") + SEASON + i + API_KEY);
            DadosTemporada dadosTemporada = converteDados.obterDados(json, DadosTemporada.class);
            listaTemporadas.add(dadosTemporada);
        }

        listaTemporadas.forEach(System.out::println);


        for(int i = 0; i < dadosSerie.totalTemporadas(); i++) {
            List<DadosEpisodio> episodiosTemporada = listaTemporadas.get(i).episodios();
            System.out.println("\nTemporada " + listaTemporadas.get(i).numero() + ": \nS");
                for(int j = 0; j < episodiosTemporada.size(); j++) {
                    System.out.println("Episódio " + episodiosTemporada.get(j).numero() + ": " + episodiosTemporada.get(j).titulo() + "\n");
                }
        }

        listaTemporadas.forEach(t -> t.episodios().forEach(e -> System.out.println("Temporada " + t.numero() + " - " + "Episodio " + e.numero() + ": " + e.titulo())));
    }

}
