package com.springcassandracrud.crudsassandraspring.services;

import com.datastax.driver.core.utils.UUIDs;
import com.springcassandracrud.crudsassandraspring.models.entity.Producto;
import com.springcassandracrud.crudsassandraspring.repository.IProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
    public ResponseEntity<Producto> crearProducto(Producto producto) {
        try {
            //UUID.randomUUID()
            Producto _producto = productoRepositorio.save(new Producto(UUIDs.timeBased(), producto.getNombre(), producto.getPrecio(), false, producto.getCantidad()));
            return new ResponseEntity<>(_producto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     * *************************************
     * 				  LEER (R)
     * *************************************
     */

    @Override
    public ResponseEntity<List<Producto>> verTodos() {
        try {
            List<Producto> productos = new ArrayList<>();

            productoRepositorio.findAll().forEach(productos::add);

            if(productos.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(productos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Producto> verPorId(UUID id) {
        // optional nos permiten recubrir un valor nulo o devolver un objeto
        Optional<Producto> productoData = productoRepositorio.findById(id);

        if (productoData.isPresent()) {
            return new ResponseEntity<>(productoData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<List<Producto>> verActivos() {
        try {
            List<Producto> productos = productoRepositorio.findByActivo(true);

            if(productos.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(productos, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     * *************************************
     * 			   ACTUALIZAR (U)
     * *************************************
     */

    @Override
    public ResponseEntity<Producto> ActualizarProducto(UUID id, Producto producto) {
        Optional<Producto> productoData = productoRepositorio.findById(id);

        if(productoData.isPresent()) {
            Producto _producto = productoData.get();

            _producto.setNombre(producto.getNombre());
            _producto.setPrecio(producto.getPrecio());
            _producto.setActivo(producto.getActivo());
            _producto.setCantidad(producto.getCantidad());

            return new ResponseEntity<>(productoRepositorio.save(_producto), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /*
     * *************************************
     * 				 ELIMINAR (D)
     * *************************************
     */

    @Override
    public ResponseEntity<HttpStatus> eliminarProductoId(UUID id) {
        try {
            productoRepositorio.deleteById(id);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<HttpStatus> eliminarProductos() {
        try {
            productoRepositorio.deleteAll();

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
