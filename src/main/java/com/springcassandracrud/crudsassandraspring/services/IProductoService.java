package com.springcassandracrud.crudsassandraspring.services;

import com.springcassandracrud.crudsassandraspring.models.entity.Producto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

public interface IProductoService {

    ResponseEntity<Producto> crearProducto(Producto producto);
    ResponseEntity<List<Producto>> verTodos();
    ResponseEntity<Producto> verPorId(UUID id);
    ResponseEntity<List<Producto>> verActivos();
    ResponseEntity<Producto> ActualizarProducto(UUID id, Producto producto);
    ResponseEntity<HttpStatus> eliminarProductoId(UUID id);
    ResponseEntity<HttpStatus> eliminarProductos();
}
