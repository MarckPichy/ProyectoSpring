package com.app.PROYECTOFINAL02.servicio;

import com.app.PROYECTOFINAL02.entidad.Estudiante;
import com.app.PROYECTOFINAL02.repositorio.EstudianteRepositiorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EstudianteServicioImpl implements EstudianteServicio{
    @Autowired
    private EstudianteRepositiorio repositiorio;


    @Override
    public List<Estudiante> listarTodosLosEstudiantes() {
        return repositiorio.findAll();
    }


    @Override
    public Estudiante guardarEstudiante(Estudiante estudiante) {
        // Verificar si ya existe un estudiante con el mismo correo electrónico
        if (repositiorio.existsByEmail(estudiante.getEmail())) {
            throw new CorreoElectronicoDuplicadoException("Ya existe un estudiante con el mismo correo electrónico");
        }

        // Guardar el estudiante si no hay duplicados
        return repositiorio.save(estudiante);
    }


    @Override
    public Estudiante obteberEstudiantePorId(Long id) {
        return repositiorio.findById(id).orElse(null);
    }


    @Override
    public Estudiante actualizarEstudiante(Estudiante estudiante) {
        return repositiorio.save(estudiante);
    }


    @Override
    public void eliminarEstudiante(Long id) {
        repositiorio.deleteById(id);
    } 
}
