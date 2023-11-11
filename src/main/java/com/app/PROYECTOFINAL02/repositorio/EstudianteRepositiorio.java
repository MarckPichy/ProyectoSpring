package com.app.PROYECTOFINAL02.repositorio;

import com.app.PROYECTOFINAL02.entidad.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstudianteRepositiorio extends JpaRepository<Estudiante, Long> {

    boolean existsByEmail(String email);

}
