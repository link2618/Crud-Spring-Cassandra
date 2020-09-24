package com.springcassandracrud.crudsassandraspring.models.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Table("productos")
@Data
//@NoArgsConstructor
//@AllArgsConstructor
public class Producto {

    @PrimaryKey
    private UUID id;
    private String nombre;
    private Double precio;
    private Boolean activo;
    private Integer cantidad;

    public Producto() {

    }

    public Producto(UUID id, String nombre, Double precio, Boolean activo, Integer cantidad) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.activo = activo;
        this.cantidad = cantidad;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
}
