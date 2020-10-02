package com.springcassandracrud.crudsassandraspring.services;

import com.datastax.driver.core.utils.UUIDs;
import com.springcassandracrud.crudsassandraspring.models.entity.Cliente;
import com.springcassandracrud.crudsassandraspring.repository.ICliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

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

        try {
//            System.out.println(((Object)cliente.getCreateAt()).getClass().getSimpleName());
//            System.out.println(cliente.getCreateAt());
            Cliente _cliente = clienteRepositorio.save(new Cliente(UUIDs.timeBased(), cliente.getNombre(), cliente.getApellido(), cliente.getEmail(), cliente.getCreateAt(), cliente.getUpdateAt()));
            return new ResponseEntity<Cliente>(_cliente, HttpStatus.CREATED);
        }catch (DataAccessException e) {
            response.put("Mensaje", "Error al realizar el insert a la BD.");
            response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
