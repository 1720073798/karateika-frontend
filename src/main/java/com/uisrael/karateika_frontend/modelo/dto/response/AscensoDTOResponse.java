package com.uisrael.karateika_frontend.modelo.dto.response;

import java.time.LocalDate;

import lombok.Data;

@Data
public class AscensoDTOResponse {

    private int asc_id_serial;
    private String asc_cinturon;
    private LocalDate asc_fecha_examen;
    private LocalDate asc_fecha_ascenso;
    private double asc_calificacion;
    private String asc_evaluador;
    private String asc_observacion;
    private boolean asc_c_generado;
    
    private AlumnoDTOResponse fkalumno;

}
