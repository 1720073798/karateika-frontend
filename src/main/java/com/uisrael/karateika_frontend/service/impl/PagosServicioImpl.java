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

	// ✅ ÚNICO MÉTODO PARA CREAR Y EDITAR (lógica del profesor)
	@Override
	public void guardarPago(PagosDTORequest dto) {
		clienteWeb.post().uri("/pagos").bodyValue(dto).retrieve().toBodilessEntity().block();
	}

	@Override
	public PagosDTOResponse buscarPorId(int id) {
		return clienteWeb.get().uri("/pagos/buscarid/{id}", id).retrieve().bodyToMono(PagosDTOResponse.class).block();
	}

	@Override
	public void eliminarPago(int id) {
		clienteWeb.delete().uri("/pagos/{id}", id).retrieve().toBodilessEntity().block();
	}
}
