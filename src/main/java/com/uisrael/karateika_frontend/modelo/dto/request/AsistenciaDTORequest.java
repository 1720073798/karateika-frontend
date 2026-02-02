package com.uisrael.karateika_frontend.modelo.dto.request;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Data;

@Data
public class AsistenciaDTORequest {
	private int asi_id;
    private LocalDate asi_fecha;
    private LocalTime asi_hora;
    private String asi_estado;     // ej: "P" (Presente), "F" (Falta)
    private String asi_observacion;
    
    private AlumnoDTORequest fkalumno;

}
