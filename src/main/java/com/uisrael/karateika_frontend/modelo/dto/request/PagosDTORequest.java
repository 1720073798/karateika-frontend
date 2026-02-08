package com.uisrael.karateika_frontend.modelo.dto.request;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class PagosDTORequest {
	
	private int pag_id;
	@DateTimeFormat (pattern = "yyyy-MM-dd")
	private LocalDate pag_fecha_pago;
	
	private String pag_metodo_pago;
	
	private String pag_numero_recibo;
	
	private String pag_observacion;
	
	private BigDecimal pag_monto;
	
	private String pag_registrado_por;
	@DateTimeFormat (pattern = "yyyy-MM-dd")
	private LocalDate pag_fecha_registro;
	
	private AlumnoDTORequest fkalumno;
	private ComprobanteDTORequest fkcomprobante;
	
}
