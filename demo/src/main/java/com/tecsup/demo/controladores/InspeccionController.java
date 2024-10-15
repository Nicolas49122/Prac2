package com.tecsup.demo.controladores;

import com.tecsup.demo.modelo.daos.InspeccionRepository;
import com.tecsup.demo.modelo.entidades.Inspeccion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import java.util.List;

@Controller
@SessionAttributes("inspeccion")
public class InspeccionController {

    @Autowired
    private InspeccionRepository inspeccionRepository;

    @RequestMapping(value = "/inspeccion", method = RequestMethod.GET)
    public String listarInspecciones(Model model) {
        List<Inspeccion> inspecciones = inspeccionRepository.findAll(); // Obtener la lista de todas las inspecciones
        model.addAttribute("inspecciones", inspecciones); // Pasar la lista al modelo
        model.addAttribute("titulo", "Listado de Inspecciones"); // Título para la vista
        return "inspeccion"; // Nombre del archivo HTML para la vista de listado
    }

    @GetMapping("/formInspeccion")
    public String mostrarFormInspeccion(Model model) {
        model.addAttribute("inspeccion", new Inspeccion()); // Crea un objeto vacío de Inspeccion
        return "formInspeccionView"; // Nombre del archivo HTML para el formulario
    }

    @PostMapping("/guardarInspeccion")
    public String guardarInspeccion(@Valid @ModelAttribute Inspeccion inspeccion, BindingResult result, SessionStatus status) {
        if (result.hasErrors()) {
            return "formInspeccionView"; // Devuelve a la vista con errores
        }
        inspeccionRepository.save(inspeccion); // Guarda la inspección
        status.setComplete(); // Marca la sesión como completa
        return "redirect:/inspeccion"; // Redirige a la lista de inspecciones
    }

    @GetMapping("/eliminarInspeccion/{id}")
    public String eliminarInspeccion(@PathVariable("id") int id) {
        inspeccionRepository.deleteById(id); // Llama al método deleteById del repositorio
        return "redirect:/inspeccion"; // Redirige a la lista de inspecciones
    }

    @GetMapping("/formInspeccion/{id}")
    public String mostrarEditarInspeccion(@PathVariable("id") int id, Model model) {
        Inspeccion inspeccion = inspeccionRepository.findById(id).orElse(null); // Obtener la inspección por ID
        if (inspeccion != null) {
            model.addAttribute("inspeccion", inspeccion); // Pasar la inspección al modelo
            return "formInspeccionView"; // Nombre del archivo HTML para el formulario de edición
        }
        return "redirect:/inspeccion"; // Redirige si no se encuentra la inspección
    }

    @GetMapping("/inspeccion/ver")
    public String verInspeccion(@RequestParam(required = false) String format, Model model) {
        List<Inspeccion> inspecciones = inspeccionRepository.findAll(); // Obtener todas las inspecciones
        model.addAttribute("inspecciones", inspecciones); // Pasar la lista al modelo
        model.addAttribute("titulo", "Lista de Inspecciones"); // Título para la vista

        if ("pdf".equals(format)) {
            return "inspeccion/ver.pdf"; // Aquí se genera el PDF
        } else if ("xlsx".equals(format)) {
            return "inspeccion/ver.xlsx"; // Aquí se genera el archivo Excel
        }

        return "inspeccion/ver"; // Devuelve la vista normal
    }
}
