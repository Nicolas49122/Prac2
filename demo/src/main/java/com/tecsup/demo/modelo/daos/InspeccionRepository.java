package com.tecsup.demo.modelo.daos;

import com.tecsup.demo.modelo.entidades.Inspeccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InspeccionRepository extends JpaRepository<Inspeccion, Integer> {
}
