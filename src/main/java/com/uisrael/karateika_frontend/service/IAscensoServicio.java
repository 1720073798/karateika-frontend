package com.uisrael.karateika_frontend.service;

import java.util.List;

import com.uisrael.karateika_frontend.modelo.dto.request.AscensoDTORequest;
import com.uisrael.karateika_frontend.modelo.dto.response.AscensoDTOResponse;

public interface IAscensoServicio {

    public List<AscensoDTOResponse> listarAscensos();

    public void crearAscenso(AscensoDTORequest dto);

    public AscensoDTOResponse buscarPorId(int id);
}