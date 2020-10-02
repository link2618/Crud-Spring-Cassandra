package com.springcassandracrud.crudsassandraspring.services;

import com.springcassandracrud.crudsassandraspring.models.entity.Cliente;
import org.springframework.http.ResponseEntity;

public interface IClienteService {

    ResponseEntity<?> crearCliente(Cliente cliente);
}
