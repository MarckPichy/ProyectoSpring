package com.app.PROYECTOFINAL02.servicio;

import com.app.PROYECTOFINAL02.entidad.Estudiante;

import java.util.List;

public interface EstudianteServicio {

    public List<Estudiante> listarTodosLosEstudiantes();

    public Estudiante guardarEstudiante(Estudiante estudiante);

    public Estudiante obteberEstudiantePorId(Long id);

    public Estudiante actualizarEstudiante(Estudiante estudiante);

    public void eliminarEstudiante(Long id);
    
}
