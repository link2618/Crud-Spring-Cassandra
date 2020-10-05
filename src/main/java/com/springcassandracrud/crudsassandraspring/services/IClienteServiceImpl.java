package com.springcassandracrud.crudsassandraspring.services;

import com.datastax.driver.core.utils.UUIDs;
import com.springcassandracrud.crudsassandraspring.models.entity.Cliente;
import com.springcassandracrud.crudsassandraspring.models.entity.Producto;
import com.springcassandracrud.crudsassandraspring.repository.ICliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class IClienteServiceImpl implements IClienteService {

    @Autowired
    ICliente clienteRepositorio;

    /*
     * *************************************
     * 				 CREAR (C)
     * *************************************
     */

    @Override
    public ResponseEntity<?> crearCliente(Cliente cliente) {
        Map<String, Object> response = new HashMap<>();
        List<Cliente> clientes = null;

        try {
//            System.out.println(((Object)cliente.getCreateAt()).getClass().getSimpleName());
//            System.out.println(cliente.getCreateAt());
            clientes = clienteRepositorio.findByEmail(cliente.getEmail());
            if(clientes.isEmpty()){
                // No existe el email en la BD
                Cliente _cliente = clienteRepositorio.save(new Cliente(UUIDs.timeBased(), cliente.getNombre(), cliente.getApellido(), cliente.getEmail(), cliente.getCreateAt(), cliente.getUpdateAt()));
                return new ResponseEntity<Cliente>(_cliente, HttpStatus.CREATED);
            } else {
                response.put("Mensaje", "El email que esta intentando registrar ya esta registrado.");
                return new ResponseEntity<Map<String, Object>>(response,HttpStatus.BAD_REQUEST);
            }

        }catch (DataAccessException e) {
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
            List<Cliente> clientes = new ArrayList<>();

            clienteRepositorio.findAll().forEach(clientes::add);

            if(clientes.isEmpty()) {
                response.put("Mensaje", "No existe informacion en la BD(esta vacia).");
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<List<Cliente>>(clientes, HttpStatus.OK);
        } catch (DataAccessException e) {
            response.put("Mensaje", "Error al realizar la consulta a la BD.");
            response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> verPorId(UUID id) {
        // optional nos permiten recubrir un valor nulo o devolver un objeto
        Optional<Cliente> clienteData = null;
        Map<String, Object> response = new HashMap<>();

        // Se verifica que todo salga bien en la BD
        try {
            clienteData = clienteRepositorio.findById(id);
        } catch (DataAccessException e){
            response.put("Mensaje", "Error al realizar la consulta a la BD.");
            response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // Si todo sale bien en la BD procedemos a enviar una respuesta al front
        if (clienteData.isPresent()) {
            return new ResponseEntity<Cliente>(clienteData.get(), HttpStatus.OK);
        } else {
            response.put("Mensaje", "El cliente no existe en la BD.");
            return new ResponseEntity<Map<String, Object>>(response,HttpStatus.NOT_FOUND);
        }
    }

    /*
     * *************************************
     * 			   ACTUALIZAR (U)
     * *************************************
     */

    @Override
    public ResponseEntity<?> actualizarCliente(UUID id, Cliente cliente) {
        Map<String, Object> response = new HashMap<>();
        Optional<Cliente> clienteData = clienteRepositorio.findById(id);
        Cliente clienteUpdate = null;

        if(clienteData.isPresent()) {
            Cliente _cliente = clienteData.get();

            _cliente.setNombre(cliente.getNombre());
            _cliente.setApellido(cliente.getApellido());
            _cliente.setEmail(cliente.getEmail());
            _cliente.setUpdateAt(cliente.getUpdateAt());

            try {
                clienteUpdate = clienteRepositorio.save(_cliente);
                return new ResponseEntity<Cliente>(clienteUpdate, HttpStatus.OK);
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
    public ResponseEntity<?> eliminarClienteId(UUID id) {
        Map<String, Object> response = new HashMap<>();

        try {
            clienteRepositorio.deleteById(id);

            response.put("Mensaje", "Se elimino de forma satisfactoria el cliente.");

            // El status 204 (NO_CONTENT) no devuelve ningun contenido el response esta demas
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NO_CONTENT);
        } catch (DataAccessException e) {
            response.put("Mensaje", "Error al Eliminar el cliente en la BD.");
            response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> eliminarClientes() {
        Map<String, Object> response = new HashMap<>();
        try {
            clienteRepositorio.deleteAll();

            response.put("Mensaje", "Se elimino de forma satisfactoria los clientes.");

            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NO_CONTENT);
        } catch (DataAccessException e) {
            response.put("Mensaje", "Error al Eliminar los clientes en la BD.");
            response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
