package com.czarate.api.futbolistas.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.czarate.api.futbolistas.model.Futbolista;

public interface IFutbolistaRepository extends
CrudRepository<Futbolista,Long>,
PagingAndSortingRepository<Futbolista,Long> {
	

}
