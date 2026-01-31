package com.uisrael.karateika_frontend.modelo.dto.response;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ComprobanteDTOResponse {

	private int com_id;
    private String com_nombre;
    private String com_ruta_archivo;
    private LocalDate com_fecha_subida;
}
