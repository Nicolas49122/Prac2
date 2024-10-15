package com.tecsup.demo.servicios;

import com.tecsup.demo.modelo.entidades.Coche;
import com.tecsup.demo.modelo.daos.CocheRepository; // Asegúrate de que esta clase existe
import com.tecsup.demo.servicios.CocheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CocheServiceImpl implements CocheService {

    @Autowired
    private CocheRepository cocheRepository;

    @Override
    public Coche guardarCoche(Coche coche) {
        return cocheRepository.save(coche);
    }

    @Override
    public Coche obtenerCochePorId(Long id) {
        return cocheRepository.findById(id).orElse(null);
    }

    @Override
    public List<Coche> obtenerTodosLosCoches() {
        return cocheRepository.findAll();
    }

    @Override
    public Coche actualizarCoche(Coche coche) {
        return cocheRepository.save(coche);
    }

    @Override
    public void eliminarCoche(Long id) {
        cocheRepository.deleteById(id);
    }

    @Override
    public List<Coche> listar() {
        return List.of();
    }
}
