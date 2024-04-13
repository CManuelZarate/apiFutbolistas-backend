package com.czarate.api.futbolistas.model;

import java.util.List;

import com.czarate.api.futbolistas.dto.PosicionDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "posiciones")
@Data
@NoArgsConstructor
public class Posicion {
	

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	private String name;
	
	@JsonIgnore//evitará que se serialicen los jugadores de fútbol cuando se serialice una posición(evitamos bucle)
	@ManyToMany(mappedBy = "positions")
	private List<Futbolista> footballPlayers;
	
	public Posicion(PosicionDTO posicionDTO) {
		this.id = posicionDTO.getId();
		this.name=posicionDTO.getName();
	}
	

}
