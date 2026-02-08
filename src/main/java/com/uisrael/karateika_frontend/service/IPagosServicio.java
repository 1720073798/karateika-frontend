package com.uisrael.karateika_frontend.service;

import java.util.List;

import com.uisrael.karateika_frontend.modelo.dto.request.PagosDTORequest;
import com.uisrael.karateika_frontend.modelo.dto.response.PagosDTOResponse;

public interface IPagosServicio {

	List<PagosDTOResponse> listarPagos();
    void guardarPago(PagosDTORequest dto); // ✅ Único método
    PagosDTOResponse buscarPorId(int id);
    void eliminarPago(int id);
}
