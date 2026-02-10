package com.uisrael.karateika_frontend.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.uisrael.karateika_frontend.modelo.dto.request.AlumnoDTORequest;
import com.uisrael.karateika_frontend.modelo.dto.response.AlumnoDTOResponse;
import com.uisrael.karateika_frontend.service.IAlumnoServicio;
@Service
public class AlumnoServiceImpl implements IAlumnoServicio{
	
	private final WebClient clienteWeb;

	public AlumnoServiceImpl(WebClient clienteWeb) {
		this.clienteWeb = clienteWeb;
	}

	@Override
	public List<AlumnoDTOResponse> listarAlumno() {
		return clienteWeb.get().uri("/alumnos").retrieve().bodyToFlux(AlumnoDTOResponse.class).collectList().block();
	}

	@Override
	public void crearAlumno(AlumnoDTORequest dto) {
		clienteWeb.post().uri("/alumnos").bodyValue(dto).retrieve().toBodilessEntity().block();
		
	}

	@Override
	public AlumnoDTOResponse buscarPorId(int idAlumno) {
		return clienteWeb.get().uri(uriBuilder -> uriBuilder.path("/alumnos/buscarid/{idAlumno}").build(idAlumno)).retrieve().bodyToMono(AlumnoDTOResponse.class).block();
	}

}
