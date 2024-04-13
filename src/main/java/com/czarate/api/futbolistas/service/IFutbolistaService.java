package com.czarate.api.futbolistas.service;

import org.springframework.data.domain.Page;

import com.czarate.api.futbolistas.dto.FutbolistaDTO;
import com.czarate.api.futbolistas.model.Futbolista;

public interface IFutbolistaService {
	public Page<Futbolista> findAll(int page,int size);
	public Futbolista findById(Long id);
	public Futbolista save(FutbolistaDTO futbolistaDTO);
}
