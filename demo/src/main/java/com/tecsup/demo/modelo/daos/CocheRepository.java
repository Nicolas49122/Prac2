package com.tecsup.demo.modelo.daos;

import com.tecsup.demo.modelo.entidades.Coche;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CocheRepository extends JpaRepository<Coche, Integer> {
}
