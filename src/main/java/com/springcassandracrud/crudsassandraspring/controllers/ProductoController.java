package com.springcassandracrud.crudsassandraspring.controllers;

import com.springcassandracrud.crudsassandraspring.models.entity.Producto;
import com.springcassandracrud.crudsassandraspring.services.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin(origins = {"http://localhost:4200"})
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
    public ResponseEntity<?> crearProducto(@RequestBody Producto producto) {
        return productoServicio.crearProducto(producto);
    }

    /*
     * *************************************
     * 				  LEER (R)
     * *************************************
     */
    @GetMapping("/ver")
    public ResponseEntity<?> verTodos(){
        return productoServicio.verTodos();
    }

    @GetMapping("/ver/{id}")
    public ResponseEntity<?> verPorId(@PathVariable UUID id){
        return productoServicio.verPorId(id);
    }

    @GetMapping("/ver/activo")
    public ResponseEntity<?> verActivos(){
        return productoServicio.verActivos();
    }

    /*
     * *************************************
     * 			   ACTUALIZAR (U)
     * *************************************
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<?> ActualizarProducto(@PathVariable UUID id, @RequestBody Producto producto) {
        return productoServicio.ActualizarProducto(id, producto);
    }

    /*
     * *************************************
     * 				 ELIMINAR (D)
     * *************************************
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> eliminarProductoId(@PathVariable UUID id) {
        return eliminarProductoId(id);
    }

    @DeleteMapping("/delete/all")
    public ResponseEntity<?> eliminarProductos() {
        return eliminarProductos();
    }
}
