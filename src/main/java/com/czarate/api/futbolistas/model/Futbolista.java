package com.czarate.api.futbolistas.model;


import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.czarate.api.futbolistas.dto.FutbolistaDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="futbolistas")
@Data
@NoArgsConstructor
public class Futbolista implements Serializable {
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	private String name;
	@NotEmpty
	private String lastName;
	
	@NotNull
	@Column(name="birth_date")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthDate;
	
	private String characteristics;
	
	@ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable(name = "tbl_futbolistas_posiciones",
    joinColumns = @JoinColumn(name="futbolista_id"),
    inverseJoinColumns = @JoinColumn(name = "posicion_id"),
    uniqueConstraints = @UniqueConstraint(columnNames = {"futbolista_id","posicion_id"}) )
	private List<Posicion> positions;
	
	public Futbolista(FutbolistaDTO futbolistaDTO,List<Posicion> lp) {
		this.name= futbolistaDTO.getName();
		this.lastName= futbolistaDTO.getLastName();
		this.birthDate= futbolistaDTO.getBirthDate();
		this.characteristics= futbolistaDTO.getCharacteristics();
		this.positions = lp;
	}
	

	private static final long serialVersionUID = 1L;
	
}
