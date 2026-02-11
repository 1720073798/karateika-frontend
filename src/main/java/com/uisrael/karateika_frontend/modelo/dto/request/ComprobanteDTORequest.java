package com.uisrael.karateika_frontend.modelo.dto.request;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class ComprobanteDTORequest {

	private Integer com_id;
    private Integer com_numero;  
    private String com_ruta_archivo;
    @DateTimeFormat (pattern = "yyyy-MM-dd")
    private LocalDate com_fecha_subida;
}
