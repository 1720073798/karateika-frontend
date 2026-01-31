package com.uisrael.karateika_frontend.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.uisrael.karateika_frontend.modelo.dto.request.ComprobanteDTORequest;
import com.uisrael.karateika_frontend.modelo.dto.response.ComprobanteDTOResponse;
import com.uisrael.karateika_frontend.service.IComprobanteServicio;

@Service
public class ComprobanteServicioImpl implements IComprobanteServicio {

	private final WebClient clienteWeb;

	public ComprobanteServicioImpl(WebClient clienteWeb) {
		this.clienteWeb = clienteWeb;
	}

	@Override
	public List<ComprobanteDTOResponse> listarComprobantes() {
		return clienteWeb.get().uri("/comprobantes").retrieve().bodyToFlux(ComprobanteDTOResponse.class).collectList()
				.block();
	}

	@Override
	public void crearComprobante(ComprobanteDTORequest dto) {
		clienteWeb.post().uri("/comprobantes").bodyValue(dto).retrieve().toBodilessEntity().block();
	}

	@Override
	public ComprobanteDTOResponse buscarPorId(int id) {
		
		return null;
	}

}
