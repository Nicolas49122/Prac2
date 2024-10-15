package com.tecsup.demo.servicios;

import com.tecsup.demo.modelo.entidades.Coche;
import java.util.List;

public interface CocheService {
    Coche guardarCoche(Coche coche);
    Coche obtenerCochePorId(Long id);
    List<Coche> obtenerTodosLosCoches();
    Coche actualizarCoche(Coche coche);
    void eliminarCoche(Long id);

    List<Coche> listar();
}
