package com.app.PROYECTOFINAL02.controlador;

import com.app.PROYECTOFINAL02.entidad.Estudiante;
import com.app.PROYECTOFINAL02.servicio.CorreoElectronicoDuplicadoException;
import com.app.PROYECTOFINAL02.servicio.EstudianteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@ControllerAdvice
public class EstudianteControlador {



@ExceptionHandler(CorreoElectronicoDuplicadoException.class)
    public ModelAndView handleEstudianteDuplicadoException(CorreoElectronicoDuplicadoException ex) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("errorMessage", ex.getMessage());
        return modelAndView;
    }


    @Autowired
    private EstudianteServicio servicio;

    @GetMapping({"/index","/"})
    public String listarEstudiantes(Model modelo){
        modelo.addAttribute("index", servicio.listarTodosLosEstudiantes());
        return "index"; //nos retorna al archiivo esrudiante
    }

    @GetMapping("/index/nuevo")
    public String crearEstudianteFormulario(Model modelo){
        Estudiante estudiante = new Estudiante();
        modelo.addAttribute("estudiante", estudiante);
        return "crear_estudiante";
    }

    @PostMapping("/index")
    public String guardarEstudiante(@ModelAttribute("estudiante") Estudiante estudiante){
        servicio.guardarEstudiante(estudiante);
        return "redirect:/index";
    }

    @GetMapping("/index/editar/{id}")
    public String mostrarFormularioDeEditar(@PathVariable Long id, Model modelo){
        modelo.addAttribute("estudiante", servicio.obteberEstudiantePorId(id));
        return "editar_estudiante";
    }

   @PostMapping("/index/{id}")
   public String actualizarEstudiantes(@PathVariable long id, @ModelAttribute("estudiante") Estudiante estudiante, Model modelo){
    Estudiante estudianteExistente = servicio.obteberEstudiantePorId(id);
    estudianteExistente.setId(id);
    estudianteExistente.setNombre(estudiante.getNombre());
    estudianteExistente.setApellido(estudiante.getApellido());
    estudianteExistente.setEmail(estudiante.getEmail());
    servicio.actualizarEstudiante(estudianteExistente);
    return "redirect:/index";
   }

    @GetMapping("/index/{id}")
    public String eliminarEstudiante(@PathVariable Long id){
        servicio.eliminarEstudiante(id);
        return "redirect:/index";
    }
}
