package com.tecsup.demo.controladores;

import com.tecsup.demo.modelo.entidades.Coche;
import com.tecsup.demo.servicios.CocheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@SessionAttributes("coche")
public class CocheController {

    @Autowired
    private CocheService cocheService;

    // Listar coches
    @RequestMapping(value = "/listarCoches", method = RequestMethod.GET)
    public String listarCoches(Model model) {
        List<Coche> coches = cocheService.listar();
        model.addAttribute("titulo", "Listado de Coches");
        model.addAttribute("coches", coches);
        return "listarCoches"; // Nombre del archivo HTML para la vista de listado
    }

    // Mostrar formulario de creación de coche
    @RequestMapping(value = "/formCoche")
    public String crear(Map<String, Object> model) {
        Coche coche = new Coche();
        model.put("coche", coche);
        model.put("titulo", "Formulario de Coche");
        return "formCoche"; // Nombre del archivo HTML para el formulario
    }

    // Mostrar formulario para editar coche
    @RequestMapping(value = "/formCoche/{id}")
    public String editar(@PathVariable(value = "id") Integer id, Map<String, Object> model) {
        Coche coche = cocheService.buscar(id);
        if (coche == null) {
            return "redirect:/listarCoches";
        }
        model.put("coche", coche);
        model.put("titulo", "Editar Coche");
        return "formCoche"; // Nombre del archivo HTML para el formulario de edición
    }

    // Guardar coche
    @RequestMapping(value = "/formCoche", method = RequestMethod.POST)
    public String guardar(@Valid Coche coche, BindingResult result, Model model, SessionStatus status) {
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Formulario de Coche");
            return "formCoche"; // Devuelve al formulario si hay errores
        }
        cocheService.grabar(coche);
        status.setComplete(); // Marca la sesión como completa
        return "redirect:/listarCoches"; // Redirige a la lista de coches
    }

    // Eliminar coche
    @RequestMapping(value = "/eliminarCoche/{id}")
    public String eliminar(@PathVariable(value = "id") Integer id) {
        if (id > 0) {
            cocheService.eliminar(id);
        }
        return "redirect:/listarCoches"; // Redirige a la lista de coches después de eliminar
    }

    // Ver coches
    @GetMapping("/coche")
    public String ver(Model model) {
        List<Coche> coches = cocheService.listar();
        model.addAttribute("titulo", "Lista de Coches");
        model.addAttribute("coches", coches);
        return "coche/ver"; // Asegúrate de que esta vista exista en la carpeta correcta
    }

    // Exportar coches a PDF o Excel
    @GetMapping("/coche/ver")
    public String verCoches(@RequestParam(required = false) String format, Model model) {
        List<Coche> coches = cocheService.listar();
        model.addAttribute("titulo", "Lista de Coches");
        model.addAttribute("coches", coches);

        if ("pdf".equals(format)) {
            return "coche/ver.pdf"; // Aquí se genera el PDF
        } else if ("xlsx".equals(format)) {
            return "coche/ver.xlsx"; // Aquí se genera el archivo Excel
        }

        return "coche/ver"; // Devuelve la vista normal
    }
}
