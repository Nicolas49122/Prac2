package com.tecsup.demo.servicios;

import com.tecsup.demo.modelo.entidades.Inspeccion;

import java.util.List;

public interface InspeccionService {

    public List<Inspeccion> listar();

    public void grabar(Inspeccion inspeccion);

    public Inspeccion buscar(Integer id);

    public void eliminar(Integer id);
}
