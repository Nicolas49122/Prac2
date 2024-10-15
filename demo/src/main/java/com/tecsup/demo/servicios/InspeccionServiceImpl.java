package com.tecsup.demo.servicios;

import com.tecsup.demo.modelo.daos.InspeccionRepository;
import com.tecsup.demo.modelo.entidades.Inspeccion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InspeccionServiceImpl implements InspeccionService {

    @Autowired
    private InspeccionRepository dao;

    @Override
    @Transactional(readOnly = false)
    public void grabar(Inspeccion inspeccion) {
        dao.save(inspeccion);
    }

    @Override
    @Transactional(readOnly = false)
    public void eliminar(Integer id) {
        dao.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Inspeccion buscar(Integer id) {
        return dao.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Inspeccion> listar() {
        return (List<Inspeccion>) dao.findAll();
    }
}
