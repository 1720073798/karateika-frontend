package com.uisrael.karateika_frontend.modelo.dto.response;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Data;

@Data
public class AsistenciaDTOResponse {
	
	private int asi_id;
    private LocalDate asi_fecha;
    private LocalTime asi_hora;
    private String asi_estado;     // ej: "P" (Presente), "F" (Falta)
    private String asi_observacion;
    
    private AlumnoDTOResponse fkalumno;

}
