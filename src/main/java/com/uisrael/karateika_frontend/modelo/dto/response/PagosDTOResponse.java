package com.uisrael.karateika_frontend.modelo.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;

@Data
public class PagosDTOResponse {

	private int pag_id;

	private int pag_mes;
	
	private int pag_anio;
	
	private LocalDate pag_fecha_pago;
	
	private String pag_metodo_pago;
	
	private String pag_numero_recibo;
	
	private String pag_observacion;
	
	private BigDecimal pag_monto;
	
	private String pag_registrado_por;
	
	private LocalDate pag_fecha_registro;
	
	public AlumnoDTOResponse fkalumno; 
	public ComprobanteDTOResponse fkcomprobante;
}
