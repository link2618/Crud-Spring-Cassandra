package com.springcassandracrud.crudsassandraspring.controllers;

import com.datastax.driver.core.utils.UUIDs;
import com.springcassandracrud.crudsassandraspring.models.entity.Producto;
import com.springcassandracrud.crudsassandraspring.repository.IProducto;
import com.springcassandracrud.crudsassandraspring.services.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/producto")
public class ProductoController {

    @Autowired
    IProductoService productoServicio;

    /*
     * *************************************
     * 				 CREAR (C)
     * *************************************
     */
    @PostMapping("/add")
    public ResponseEntity<Producto> crearProducto(@RequestBody Producto producto) {
        return productoServicio.crearProducto(producto);
    }

    /*
     * *************************************
     * 				  LEER (R)
     * *************************************
     */
    @GetMapping("/ver")
    public ResponseEntity<List<Producto>> verTodos(){
        return productoServicio.verTodos();
    }

    @GetMapping("/ver/{id}")
    public ResponseEntity<Producto> verPorId(@PathVariable UUID id){
        return productoServicio.verPorId(id);
    }

    @GetMapping("/ver/activo")
    public ResponseEntity<List<Producto>> verActivos(){
        return productoServicio.verActivos();
    }

    /*
     * *************************************
     * 			   ACTUALIZAR (U)
     * *************************************
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<Producto> ActualizarProducto(@PathVariable UUID id, @RequestBody Producto producto) {
        return productoServicio.ActualizarProducto(id, producto);
    }

    /*
     * *************************************
     * 				 ELIMINAR (D)
     * *************************************
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> eliminarProductoId(@PathVariable UUID id) {
        return eliminarProductoId(id);
    }

    @DeleteMapping("/delete/all")
    public ResponseEntity<HttpStatus> eliminarProductos() {
        return eliminarProductos();
    }
}
