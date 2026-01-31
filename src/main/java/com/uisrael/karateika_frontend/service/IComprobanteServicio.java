package com.uisrael.karateika_frontend.service;

import java.util.List;


import com.uisrael.karateika_frontend.modelo.dto.request.ComprobanteDTORequest;
import com.uisrael.karateika_frontend.modelo.dto.response.ComprobanteDTOResponse;

public interface IComprobanteServicio {

		public List<ComprobanteDTOResponse> listarComprobantes();
		
		public void crearComprobante(ComprobanteDTORequest dto);
		
		public ComprobanteDTOResponse buscarPorId(int id);
}
