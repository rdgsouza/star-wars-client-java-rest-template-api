package com.testopah.starwars.client.assembler;

import org.modelmapper.ModelMapper;

import com.testopah.starwars.client.domain.model.Films;
import com.testopah.starwars.client.representationmodel.FilmsRepresentationModel;

public class FilmsModelAssembler {

	ModelMapper modelMapper = new ModelMapper();

	public FilmsRepresentationModel toModel(Films films) {

		return modelMapper.map(films, FilmsRepresentationModel.class);
	}

}