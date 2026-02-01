package com.uisrael.karateika_frontend.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.uisrael.karateika_frontend.modelo.dto.request.AscensoDTORequest;
import com.uisrael.karateika_frontend.modelo.dto.response.AscensoDTOResponse;
import com.uisrael.karateika_frontend.service.IAscensoServicio;

@Service
public class AscensoServicioImpl implements IAscensoServicio {

	private final WebClient clienteWeb;

	public AscensoServicioImpl(WebClient clienteWeb) {
		this.clienteWeb = clienteWeb;
	}

	@Override
	public List<AscensoDTOResponse> listarAscensos() {
		return clienteWeb.get().uri("/ascensos").retrieve().bodyToFlux(AscensoDTOResponse.class).collectList().block();
	}

	@Override
	public void crearAscenso(AscensoDTORequest dto) {
		clienteWeb.post().uri("/ascensos").bodyValue(dto).retrieve().toBodilessEntity().block();
	}

	@Override
	public AscensoDTOResponse buscarPorId(int id) {
		// Se puede implementar luego si lo necesitas
		return null;
	}
}