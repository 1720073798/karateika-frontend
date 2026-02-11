package com.uisrael.karateika_frontend.service.impl;

import java.util.List;

import org.springframework.http.MediaType;
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
		try {
			clienteWeb.post().uri("/ascensos").bodyValue(dto).retrieve().toBodilessEntity().block();
		} catch (org.springframework.web.reactive.function.client.WebClientResponseException e) {
			String body = e.getResponseBodyAsString();
			throw new RuntimeException("API error: status " + e.getStatusCode() + " body: " + body, e);
		}
	}

	@Override
	public AscensoDTOResponse buscarPorId(int idAscenso) {
		// Se puede implementar luego si lo necesitas
		return clienteWeb.get().uri(uriBuilder -> uriBuilder.path("/ascensos/buscarid/{idAscenso}").build(idAscenso)).retrieve().bodyToMono(AscensoDTOResponse.class).block();
	}

	@Override
	public byte[] crearAscensoYDescargarCertificado(AscensoDTORequest dto) {
		// Llamamos al endpoint que puede devolver PDF
		return clienteWeb.post()
				.uri("/ascensos/certificado")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_PDF)
				.bodyValue(dto)
				.retrieve()
				.bodyToMono(byte[].class)
				.block();
	}
}