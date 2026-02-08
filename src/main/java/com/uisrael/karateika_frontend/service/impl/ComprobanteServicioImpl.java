package com.uisrael.karateika_frontend.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserters;
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

	// ✅ ENVÍA MULTIPART CON ARCHIVO
	@Override
	public void guardarComprobante(ComprobanteDTORequest dto, MultipartFile archivo) {
		MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();

		// Parte 1: DTO como JSON en el campo "comprobante"
		body.add("comprobante", dto);

		// Parte 2: Archivo en el campo "archivo" (si existe)
		if (archivo != null && !archivo.isEmpty()) {
			try {
				ByteArrayResource resource = new ByteArrayResource(archivo.getBytes()) {
					@Override
					public String getFilename() {
						return archivo.getOriginalFilename();
					}
				};
				body.add("archivo", resource);
			} catch (IOException e) {
				throw new RuntimeException("Error al procesar el archivo", e);
			}
		}

		clienteWeb.post().uri("/comprobantes").contentType(MediaType.MULTIPART_FORM_DATA)
				.body(BodyInserters.fromMultipartData(body)).retrieve().toBodilessEntity().block();
	}

	@Override
	public ComprobanteDTOResponse buscarPorId(int id) {
		return clienteWeb.get().uri("/comprobantes/buscarid/{id}", id).retrieve()
				.bodyToMono(ComprobanteDTOResponse.class).block();
	}

	@Override
	public void eliminarComprobante(int id) {
		clienteWeb.delete().uri("/comprobantes/{id}", id).retrieve().toBodilessEntity().block();
	}
}
