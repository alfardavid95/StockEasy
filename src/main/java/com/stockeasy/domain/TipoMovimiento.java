package com.stockeasy.domain;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tipo_movimiento")
public class TipoMovimiento implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_movimiento")
    private Integer idTipoMovimiento;

    private String nombre;
    private String descripcion;

    public Integer getIdTipoMovimiento() { return idTipoMovimiento; }
    public void setIdTipoMovimiento(Integer idTipoMovimiento) { this.idTipoMovimiento = idTipoMovimiento; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}
