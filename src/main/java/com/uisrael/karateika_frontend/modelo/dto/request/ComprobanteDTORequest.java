package com.uisrael.karateika_frontend.modelo.dto.request;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ComprobanteDTORequest {

    private int com_id;
    private String com_nombre;
    private String com_ruta_archivo;
    private LocalDate com_fecha_subida;
}
