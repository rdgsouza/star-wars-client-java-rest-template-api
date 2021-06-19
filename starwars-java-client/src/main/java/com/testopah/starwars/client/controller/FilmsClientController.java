package com.testopah.starwars.client.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.testopah.starwars.client.assembler.FilmsModelAssembler;
import com.testopah.starwars.client.domain.model.Films;
import com.testopah.starwars.client.exception.ClientApiException;
import com.testopah.starwars.client.representationmodel.FilmsRepresentationModel;
import com.testopah.starwars.client.service.FilmsClientService;

public class FilmsClientController {

	static FilmsModelAssembler filmsModelAssembler = new FilmsModelAssembler();

	public static void listaFilmes() {

		try {

			RestTemplate restTemplate = new RestTemplate();
			String uri = "https://swapi.dev/api/films/";

			FilmsClientService filmsClientService = new FilmsClientService(restTemplate, uri);

			List<String> films = filmsClientService.ListarFilmes();

			System.out.println("\n*** Informações dos filmes em paginação ***");

			films.stream().forEach(f -> System.out.println(f));

    		} catch (ClientApiException e) {
		}
	}

	public static void buscarFilmPorId(String idFilme) {

		RestTemplate restTemplate = new RestTemplate();

		FilmsClientService filmsClientService = new FilmsClientService(restTemplate,
				"https://swapi.dev/api/films/".concat(idFilme) + "/");

		ResponseEntity<Films> f = filmsClientService.buscaFilmePorId();

		Films film  = f.getBody();

		FilmsRepresentationModel filmRepresentationModel = filmsModelAssembler.toModel(film);

		System.out.println("\n*** Informação do filme ***\n" + filmRepresentationModel.toString());

	}

	public static void buscarfilmesPorNome(String nome) {

		RestTemplate restTemplate = new RestTemplate();
		String uri = "https://swapi.dev/api/films/";

		UriComponents uriComParametros = UriComponentsBuilder.fromHttpUrl(uri).queryParam("search", nome).build();

		FilmsClientService filmsClientService = new FilmsClientService(restTemplate, uriComParametros.toString());

		List<String> films = filmsClientService.buscaFilmesPorNome();

		System.out.println("\n*** Informação dos filmes em paginação ***");

		films.stream().forEach(f -> System.out.println(f));

	}

}
