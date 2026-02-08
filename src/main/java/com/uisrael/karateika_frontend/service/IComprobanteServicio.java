package com.uisrael.karateika_frontend.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.uisrael.karateika_frontend.modelo.dto.request.ComprobanteDTORequest;
import com.uisrael.karateika_frontend.modelo.dto.response.ComprobanteDTOResponse;

public interface IComprobanteServicio {

	List<ComprobanteDTOResponse> listarComprobantes();

	void guardarComprobante(ComprobanteDTORequest dto, MultipartFile archivo); 

	ComprobanteDTOResponse buscarPorId(int id);

	void eliminarComprobante(int id);
}
