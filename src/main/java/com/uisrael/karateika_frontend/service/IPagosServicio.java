package com.uisrael.karateika_frontend.service;

import java.util.List;

import com.uisrael.karateika_frontend.modelo.dto.request.PagosDTORequest;
import com.uisrael.karateika_frontend.modelo.dto.response.PagosDTOResponse;

public interface IPagosServicio {

	public List<PagosDTOResponse> listarPagos();

	public void crearPago(PagosDTORequest dto);

	public PagosDTOResponse buscarPorId(int id);
}
