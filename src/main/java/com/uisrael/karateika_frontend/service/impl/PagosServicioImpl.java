package com.uisrael.karateika_frontend.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.uisrael.karateika_frontend.modelo.dto.request.PagosDTORequest;
import com.uisrael.karateika_frontend.modelo.dto.response.PagosDTOResponse;
import com.uisrael.karateika_frontend.service.IPagosServicio;

@Service
public class PagosServicioImpl implements IPagosServicio {

	private final WebClient clienteWeb;

	public PagosServicioImpl(WebClient clienteWeb) {
		this.clienteWeb = clienteWeb;
	}

	@Override
	public List<PagosDTOResponse> listarPagos() {
		return clienteWeb.get().uri("/pagos").retrieve().bodyToFlux(PagosDTOResponse.class).collectList().block();
	}

	@Override
	public void crearPago(PagosDTORequest dto) {
		clienteWeb.post().uri("/pagos").bodyValue(dto).retrieve().toBodilessEntity().block();
	}

	@Override
	public PagosDTOResponse buscarPorId(int id) {
		
		return null;
	}

}
