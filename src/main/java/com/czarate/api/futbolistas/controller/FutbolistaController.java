package com.czarate.api.futbolistas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.czarate.api.futbolistas.dto.FutbolistaDTO;
import com.czarate.api.futbolistas.model.Futbolista;
import com.czarate.api.futbolistas.service.IFutbolistaService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/futbolista")
@Slf4j
@CrossOrigin("*")
public class FutbolistaController {

	@Autowired
	private IFutbolistaService futbolistaService;
	
	@GetMapping("")
	public ResponseEntity<Page<Futbolista>> getFutbolistas(
			@RequestParam( defaultValue = "0" ) int page,
			@RequestParam( defaultValue = "10" ) int size
			){
		try {
			Page<Futbolista> players = futbolistaService.findAll(page,size);
			return ResponseEntity.status(HttpStatus.OK).body(players);
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.internalServerError().build();
		}
	}
	
	@GetMapping("/{idPlayer}")
	public ResponseEntity<Futbolista> getFutbolista(@PathVariable Long idPlayer){
		try {
			Futbolista player = futbolistaService.findById(idPlayer);
			return ResponseEntity.status(HttpStatus.OK).body(player);
			
 		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.internalServerError().build();
		}
	}
	
	
	@PostMapping("/create")
	@Transactional
	public ResponseEntity<?> createFutbolista(@Valid @RequestBody FutbolistaDTO futbolistaDTO,BindingResult result){
		try {
			log.info("futbolista  "+futbolistaDTO);
			 if (result.hasErrors()) {
		        log.info("Error campos no validos");
		        return ResponseEntity.unprocessableEntity().body("Error campos no validos");
			 }
			Futbolista player = futbolistaService.save(futbolistaDTO);
			log.info("Jugador creado");
			return ResponseEntity.status(HttpStatus.CREATED).body(player);
		}
		catch(Exception ex) {
			log.error(ex.getMessage());
			return ResponseEntity.internalServerError().build();
		}
	}
	
	
	
}
