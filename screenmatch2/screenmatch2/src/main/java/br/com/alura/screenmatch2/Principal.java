package br.com.alura.screenmatch2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.com.alura.model.DadosSerie;
import br.com.alura.model.DadosTemporada;
import br.com.alura.service.ConsumirAPI;
import br.com.alura.service.ConverteDados;

public class Principal {

    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=176e2e95";
    
    public void exebirMenu(String serie) {
        var consumoapi = new ConsumirAPI();
		var converteDados = new ConverteDados();
        Scanner scanner = new Scanner(System.in);

		var json = consumoapi.obterDados(ENDERECO + serie + API_KEY);
		DadosSerie dadosSerie = converteDados.obterDados(json, DadosSerie.class);
		System.out.println(dadosSerie);

        List<DadosTemporada> listaTemporadas = new ArrayList<>();

		for (int i = 1; i <= dadosSerie.totalTemporadas(); i++) {
			json = consumoapi.obterDados(ENDERECO + serie + "&season=" + i + API_KEY);
			DadosTemporada dadosTemporada = converteDados.obterDados(json, DadosTemporada.class);
			listaTemporadas.add(dadosTemporada);
		}
		
		listaTemporadas.forEach(System.out::println);
    }

}
