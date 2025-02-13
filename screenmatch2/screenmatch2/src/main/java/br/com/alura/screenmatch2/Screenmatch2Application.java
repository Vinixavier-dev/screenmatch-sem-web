package br.com.alura.screenmatch2;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Screenmatch2Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Screenmatch2Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal();
		Scanner scanner = new Scanner(System.in);

		System.out.println("Escreva uma série: ");
        String serie = scanner.nextLine();
		principal.exebirMenu(serie);
	}
}
