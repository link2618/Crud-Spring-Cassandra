package com.springcassandracrud.crudsassandraspring.services;

import com.springcassandracrud.crudsassandraspring.models.entity.Producto;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface IProductoService {

    // Al poner ? en el tipo recibe cualquier tipo de dato
    ResponseEntity<?> crearProducto(Producto producto);
    ResponseEntity<?> verTodos();
    ResponseEntity<?> verPorId(UUID id);
    ResponseEntity<?> verActivos();
    ResponseEntity<?> ActualizarProducto(UUID id, Producto producto);
    ResponseEntity<?> eliminarProductoId(UUID id);
    ResponseEntity<?> eliminarProductos();
}
