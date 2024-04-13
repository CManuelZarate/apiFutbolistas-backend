package com.czarate.api.futbolistas.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.czarate.api.futbolistas.dto.FutbolistaDTO;
import com.czarate.api.futbolistas.model.Futbolista;
import com.czarate.api.futbolistas.model.Posicion;
import com.czarate.api.futbolistas.repository.IFutbolistaRepository;

@Service
public class FutbolistaService implements IFutbolistaService {
	
	@Autowired
	private IFutbolistaRepository futbolistaRepository; 

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
		for(int i=0;i<futbolistaDTO.getPositionsDTO().size();i++) {
			lp.add(new Posicion(futbolistaDTO.getPositionsDTO().get(i))) ;
		}
		
		Futbolista f = new Futbolista(futbolistaDTO,lp);
		
		return futbolistaRepository.save(f); 
	}

}
