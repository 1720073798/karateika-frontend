package com.uisrael.karateika_frontend.modelo.dto.request;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class AlumnoDTORequest {
	
	private int alu_id;
	private String alu_cedula;
	private String alu_nombre;
	private String alu_apellido;
	private String alu_direccion;
	private String alu_telefono;
	private String alu_email;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate alu_fecha_nacimiento;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate alu_fecha_ingreso;
	private String alu_cinturon_ingreso;
	private String alu_cinturon_actual;
	private String alu_nombre_representante;
	private String alu_telefono_representante;
	private char alu_estado;
	private boolean alu_alerta_pago;
	private Date alu_fecha_creacion;
	private Date alu_fecha_modificacion;
	
}