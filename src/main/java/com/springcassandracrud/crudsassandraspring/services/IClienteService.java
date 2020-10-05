package com.springcassandracrud.crudsassandraspring.services;

import com.springcassandracrud.crudsassandraspring.models.entity.Cliente;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface IClienteService {

    ResponseEntity<?> crearCliente(Cliente cliente);
    ResponseEntity<?> verTodos();
    ResponseEntity<?> verPorId(UUID id);
    ResponseEntity<?> actualizarCliente(UUID id, Cliente cliente);
    ResponseEntity<?> eliminarClienteId(UUID id);
    ResponseEntity<?> eliminarClientes();
}
