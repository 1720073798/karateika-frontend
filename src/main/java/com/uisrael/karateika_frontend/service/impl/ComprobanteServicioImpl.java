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
import org.springframework.web.reactive.function.client.WebClientResponseException;

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

		try {
			clienteWeb.post().uri("/comprobantes").contentType(MediaType.MULTIPART_FORM_DATA)
					.body(BodyInserters.fromMultipartData(body)).retrieve().toBodilessEntity().block();
		} catch (WebClientResponseException wcre) {
			// Extract backend body and try to parse a friendly message
			String bodyStr = wcre.getResponseBodyAsString();
			String message = null;
			if (bodyStr != null && !bodyStr.isBlank()) {
				// Try JSON parsing (fields: message, error, errors)
				try {
					com.fasterxml.jackson.databind.ObjectMapper om = new com.fasterxml.jackson.databind.ObjectMapper();
					com.fasterxml.jackson.databind.JsonNode root = om.readTree(bodyStr);
					if (root.has("message")) {
						message = root.get("message").asText();
					} else if (root.has("error")) {
						// Some backends return {"error":"400 BAD_REQUEST \"msg\""}
						message = root.get("error").asText();
					} else if (root.has("errors")) {
						message = root.get("errors").toString();
					} else {
						message = bodyStr;
					}
				} catch (Exception ex) {
					// Fallback: extract first quoted substring like "El número..."
					java.util.regex.Matcher m = java.util.regex.Pattern.compile("\"([^\"]+)\"").matcher(bodyStr);
					if (m.find()) {
						message = m.group(1);
					} else {
						// If nothing, use the raw body
						message = bodyStr;
					}
				}
			} else {
				message = wcre.getMessage();
			}

			// In case message still contains status prefix, try to remove common prefixes
			if (message != null) {
				// remove leading status info like '400 BAD_REQUEST ' or 'Bad Request: '
				message = message.replaceAll("^\\s*\\d{3}\\s+[^\"]*\\s*", "").trim();
				// if message was quoted twice, unwrap
				if (message.startsWith("\"") && message.endsWith("\"")) {
					message = message.substring(1, message.length() - 1).trim();
				}
			}

			throw new RuntimeException(message, wcre);
		}
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