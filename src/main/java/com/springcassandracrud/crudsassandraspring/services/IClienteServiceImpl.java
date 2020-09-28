package com.springcassandracrud.crudsassandraspring.services;

import com.datastax.driver.core.utils.UUIDs;
import com.springcassandracrud.crudsassandraspring.models.entity.Cliente;
import com.springcassandracrud.crudsassandraspring.repository.ICliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
    public ResponseEntity<Cliente> crearCliente(Cliente cliente) {
        try {
            System.out.println(((Object)cliente.getCreateAt()).getClass().getSimpleName());
            System.out.println(cliente.getCreateAt());
            Cliente _cliente = clienteRepositorio.save(new Cliente(UUIDs.timeBased(), cliente.getNombre(), cliente.getApellido(), cliente.getEmail(), cliente.getCreateAt(), cliente.getUpdateAt()));
            return new ResponseEntity<>(_cliente, HttpStatus.CREATED);
        }catch (Exception e ) {
            System.out.printf("Error");
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}