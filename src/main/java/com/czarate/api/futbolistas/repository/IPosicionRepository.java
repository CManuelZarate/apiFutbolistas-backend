package com.czarate.api.futbolistas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.czarate.api.futbolistas.model.Posicion;

public interface IPosicionRepository extends
JpaRepository<Posicion, Long>{
	Optional<Posicion> findByName(String name);
}
