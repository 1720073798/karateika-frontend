package com.uisrael.karateika_frontend.modelo.dto.response;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class ComprobanteDTOResponse {

	private int com_id;
    private int com_numero;  
    private String com_ruta_archivo;
    @DateTimeFormat (pattern = "yyyy-MM-dd")
    private LocalDate com_fecha_subida;
}
