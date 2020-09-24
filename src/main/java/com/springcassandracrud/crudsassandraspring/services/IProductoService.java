package com.springcassandracrud.crudsassandraspring.services;

import com.springcassandracrud.crudsassandraspring.models.entity.Producto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

public interface IProductoService {

    ResponseEntity<Producto> crearProducto(@RequestBody Producto producto);
    ResponseEntity<List<Producto>> verTodos();
    ResponseEntity<Producto> verPorId(@PathVariable UUID id);
    ResponseEntity<List<Producto>> verActivos();
    ResponseEntity<Producto> ActualizarProducto(@PathVariable UUID id, @RequestBody Producto producto);
    ResponseEntity<HttpStatus> eliminarProductoId(@PathVariable UUID id);
    ResponseEntity<HttpStatus> eliminarProductos();
}
