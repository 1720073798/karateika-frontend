package com.uisrael.karateika_frontend.service;

import java.util.List;

import com.uisrael.karateika_frontend.modelo.dto.request.AlumnoDTORequest;
import com.uisrael.karateika_frontend.modelo.dto.response.AlumnoDTOResponse;


public interface IAlumnoServicio {
    public List<AlumnoDTOResponse> listarAlumno();

    public void crearAlumno(AlumnoDTORequest dto);

    public AlumnoDTOResponse buscarPorId(int id);

}
