package com.uisrael.karateika_frontend.modelo.dto.request;

import java.time.LocalDate;

import lombok.Data;

@Data
public class AscensoDTORequest {

    private int asc_id_serial;
    private String asc_cinturon;
    private LocalDate asc_fecha_examen;
    private LocalDate asc_fecha_ascenso;
    private double asc_calificacion;
    private String asc_evaluador;
    private String asc_observacion;
    private boolean asc_c_generado;

}
