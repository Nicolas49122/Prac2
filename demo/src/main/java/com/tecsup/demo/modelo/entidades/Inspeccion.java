package com.tecsup.demo.modelo.entidades;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "inspecciones") // Nombre de la tabla en la base de datos
public class Inspeccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_coche")
    private Long idCoche;

    private LocalDate fecha;
    private String resultado;
    private String comentarios;

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdCoche() {
        return idCoche;
    }

    public void setIdCoche(Long idCoche) {
        this.idCoche = idCoche;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }
}
