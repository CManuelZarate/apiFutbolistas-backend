package com.czarate.api.futbolistas.dto;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FutbolistaDTO {
	@NotEmpty
	private String name;
	@NotEmpty
	private String lastName;
	
	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthDate;
	
	private String characteristics;
	private List<PosicionDTO> positionsDTO; 
	
}
