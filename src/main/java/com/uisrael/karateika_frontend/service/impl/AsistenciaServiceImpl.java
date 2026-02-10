package com.uisrael.karateika_frontend.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.uisrael.karateika_frontend.modelo.dto.request.AsistenciaDTORequest;
import com.uisrael.karateika_frontend.modelo.dto.response.AsistenciaDTOResponse;
import com.uisrael.karateika_frontend.service.IAsistenciaService;

@Service
public class AsistenciaServiceImpl implements IAsistenciaService{
	
	private final WebClient clienteWeb;
	

	public AsistenciaServiceImpl(WebClient clienteWeb) {
		this.clienteWeb = clienteWeb;
	}

	@Override
	public List<AsistenciaDTOResponse> listarAsistencia() {
		return clienteWeb.get().uri("/asistencias").retrieve().bodyToFlux(AsistenciaDTOResponse.class).collectList().block();
	}

	@Override
	public void crearAsistencia(AsistenciaDTORequest dto) {
		clienteWeb.post().uri("/asistencias").bodyValue(dto).retrieve().toBodilessEntity().block();
		
	}

	@Override
	public AsistenciaDTOResponse buscarPorId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void guardarAsistencias(List<AsistenciaDTORequest> asistencias) {
		clienteWeb.post().uri("/asistencias/batch").bodyValue(asistencias).retrieve().toBodilessEntity().block();	
	}

	

}
