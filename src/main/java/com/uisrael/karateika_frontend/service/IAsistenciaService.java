package com.uisrael.karateika_frontend.service;

import java.util.List;

import com.uisrael.karateika_frontend.modelo.dto.request.AsistenciaDTORequest;
import com.uisrael.karateika_frontend.modelo.dto.response.AsistenciaDTOResponse;

public interface IAsistenciaService {
    public List<AsistenciaDTOResponse> listarAsistencia();

    public void crearAsistencia(AsistenciaDTORequest dto);

    public AsistenciaDTOResponse buscarPorId(int id);
 

}
