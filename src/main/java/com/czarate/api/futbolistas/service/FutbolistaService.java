package com.czarate.api.futbolistas.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.czarate.api.futbolistas.dto.FutbolistaDTO;
import com.czarate.api.futbolistas.dto.PosicionDTO;
import com.czarate.api.futbolistas.model.Futbolista;
import com.czarate.api.futbolistas.model.Posicion;
import com.czarate.api.futbolistas.repository.IFutbolistaRepository;
import com.czarate.api.futbolistas.repository.IPosicionRepository;

@Service
public class FutbolistaService implements IFutbolistaService {
	
	@Autowired
	private IFutbolistaRepository futbolistaRepository; 
	
	@Autowired
	private IPosicionRepository posicionRepository;

	@Override
	public Page<Futbolista> findAll(int page,int size) {
		//return (List<Futbolista>) futbolistaRepository.findAll();
		PageRequest pageRequest = PageRequest.of(page, size);
		return futbolistaRepository.findAll(pageRequest);
	}

	@Override
	public Futbolista findById(Long id) {
		return futbolistaRepository.findById(id).get();
	}

	@Override
	public Futbolista save(FutbolistaDTO futbolistaDTO) {
		
		List<Posicion> lp =  new ArrayList<>();
		 for (PosicionDTO posicionDTO : futbolistaDTO.getPositionsDTO()) {
		   Posicion posicion = posicionRepository.findByName(posicionDTO.getName())
		            .orElse(new Posicion(posicionDTO));
		   lp.add(posicion);
		    }
		
		Futbolista f = new Futbolista(futbolistaDTO,lp);
		
		return futbolistaRepository.save(f); 
	}

}
