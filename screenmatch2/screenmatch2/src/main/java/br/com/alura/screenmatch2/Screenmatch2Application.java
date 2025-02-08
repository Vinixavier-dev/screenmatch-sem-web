package br.com.alura.screenmatch2;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.alura.model.DadosSerie;
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

		var json = consumoapi.obterDados("https://www.omdbapi.com/?t=lost&apikey=176e2e95");

		System.out.println(json);

		var converteDados = new ConverteDados();

		DadosSerie dadosSerie = converteDados.obterDados(json, DadosSerie.class);
		
		System.out.println(dadosSerie);
}
}
