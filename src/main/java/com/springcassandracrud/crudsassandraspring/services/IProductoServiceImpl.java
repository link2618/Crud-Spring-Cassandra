package com.springcassandracrud.crudsassandraspring.services;

import com.datastax.driver.core.utils.UUIDs;
import com.springcassandracrud.crudsassandraspring.models.entity.Producto;
import com.springcassandracrud.crudsassandraspring.repository.IProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class IProductoServiceImpl implements IProductoService {

    @Autowired
    IProducto productoRepositorio;

    /*
     * *************************************
     * 				 CREAR (C)
     * *************************************
     */

    @Override
    public ResponseEntity<?> crearProducto(Producto producto) {
        Map<String, Object> response = new HashMap<>();

        try {
            //UUID.randomUUID()
            Producto _producto = productoRepositorio.save(new Producto(UUIDs.timeBased(), producto.getNombre(), producto.getPrecio(), false, producto.getCantidad()));
            return new ResponseEntity<Producto>(_producto, HttpStatus.CREATED);
        } catch (DataAccessException e) {
            response.put("Mensaje", "Error al realizar el insert a la BD.");
            response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     * *************************************
     * 				  LEER (R)
     * *************************************
     */

    @Override
    public ResponseEntity<?> verTodos() {
        Map<String, Object> response = new HashMap<>();

        try {
            List<Producto> productos = new ArrayList<>();

            productoRepositorio.findAll().forEach(productos::add);

            if(productos.isEmpty()) {
                response.put("Mensaje", "No existe informacion en la BD(esta vacia).");
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<List<Producto>>(productos, HttpStatus.OK);
        } catch (DataAccessException e) {
            response.put("Mensaje", "Error al realizar la consulta a la BD.");
            response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> verPorId(UUID id) {
        // optional nos permiten recubrir un valor nulo o devolver un objeto
        Optional<Producto> productoData = null;
        Map<String, Object> response = new HashMap<>();

        // Se verifica que todo salga bien en la BD
        try {
            productoData = productoRepositorio.findById(id);
        } catch (DataAccessException e){
            response.put("Mensaje", "Error al realizar la consulta a la BD.");
            response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // Si todo sale bien en la BD procedemos a enviar una respuesta al front
        if (productoData.isPresent()) {
            return new ResponseEntity<Producto>(productoData.get(), HttpStatus.OK);
        } else {
            response.put("Mensaje", "El producto no existe en la BD.");
            return new ResponseEntity<Map<String, Object>>(response,HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<?> verActivos() {
        Map<String, Object> response = new HashMap<>();

        try {
            List<Producto> productos = productoRepositorio.findByActivo(true);

            if(productos.isEmpty()) {
                response.put("Mensaje", "No hay productos activos en la BD.");
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<List<Producto>>(productos, HttpStatus.OK);

        } catch (DataAccessException e) {
            response.put("Mensaje", "Error al realizar la consulta a la BD.");
            response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     * *************************************
     * 			   ACTUALIZAR (U)
     * *************************************
     */

    @Override
    public ResponseEntity<?> ActualizarProducto(UUID id, Producto producto) {
        Map<String, Object> response = new HashMap<>();
        Optional<Producto> productoData = productoRepositorio.findById(id);
        Producto productoUpdate = null;

        if(productoData.isPresent()) {
            Producto _producto = productoData.get();

            _producto.setNombre(producto.getNombre());
            _producto.setPrecio(producto.getPrecio());
            _producto.setActivo(producto.getActivo());
            _producto.setCantidad(producto.getCantidad());

            try {
                productoUpdate = productoRepositorio.save(_producto);
                return new ResponseEntity<Producto>(productoUpdate, HttpStatus.OK);
            } catch (DataAccessException e) {
                response.put("Mensaje", "Error actualizar el producto en la BD.");
                response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            response.put("Mensaje", "No existe el producto que desea actualizar en la BD.");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
    }

    /*
     * *************************************
     * 				 ELIMINAR (D)
     * *************************************
     */

    @Override
    public ResponseEntity<?> eliminarProductoId(UUID id) {
        Map<String, Object> response = new HashMap<>();

        try {
            productoRepositorio.deleteById(id);

            response.put("Mensaje", "E elimino de forma satisfactoria el producto.");

            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NO_CONTENT);
        } catch (DataAccessException e) {
            response.put("Mensaje", "Error al Eliminar el producto en la BD.");
            response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> eliminarProductos() {
        Map<String, Object> response = new HashMap<>();
        try {
            productoRepositorio.deleteAll();

            response.put("Mensaje", "E elimino de forma satisfactoria los productos.");

            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NO_CONTENT);
        } catch (DataAccessException e) {
            response.put("Mensaje", "Error al Eliminar los productos en la BD.");
            response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
