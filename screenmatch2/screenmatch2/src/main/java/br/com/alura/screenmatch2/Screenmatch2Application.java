package br.com.alura.screenmatch2;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.alura.model.DadosEpisodio;
import br.com.alura.model.DadosSerie;
import br.com.alura.model.DadosTemporada;
import br.com.alura.service.ConsumirAPI;
import br.com.alura.service.ConverteDados;

@SpringBootApplication
public class Screenmatch2Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Screenmatch2Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var consumoapi = new ConsumirAPI();
		var converteDados = new ConverteDados();

		var json = consumoapi.obterDados("https://www.omdbapi.com/?t=lost&apikey=176e2e95");
		DadosSerie dadosSerie = converteDados.obterDados(json, DadosSerie.class);
		System.out.println(dadosSerie);

		json = consumoapi.obterDados("https://www.omdbapi.com/?t=lost&season=2&episode=3&apikey=176e2e95");
		DadosEpisodio dadosEpisodio = converteDados.obterDados(json, DadosEpisodio.class);
		System.out.println(dadosEpisodio);

		List<DadosTemporada> listaTemporadas = new ArrayList<>();

		for (int i = 1; i <= dadosSerie.totalTemporadas(); i++) {
			json = consumoapi.obterDados("https://www.omdbapi.com/?t=lost&season=" + i + "&apikey=176e2e95");
			DadosTemporada dadosTemporada = converteDados.obterDados(json, DadosTemporada.class);
			listaTemporadas.add(dadosTemporada);
		}
		
		listaTemporadas.forEach(System.out::println);
	}
}
