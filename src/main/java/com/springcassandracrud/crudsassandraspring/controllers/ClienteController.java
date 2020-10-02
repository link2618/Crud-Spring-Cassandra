package com.springcassandracrud.crudsassandraspring.controllers;

import com.springcassandracrud.crudsassandraspring.models.entity.Cliente;
import com.springcassandracrud.crudsassandraspring.services.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

    @Autowired
    IClienteService clienteSevicio;

    /*
     * *************************************
     * 				 CREAR (C)
     * *************************************
     */
    @PostMapping("/add")
    public ResponseEntity<?> crearCliente(@RequestBody Cliente cliente) {
        LocalDate ahora = LocalDate.now();

        cliente.setCreateAt(ahora);
        cliente.setUpdateAt(ahora);

        return clienteSevicio.crearCliente(cliente);
    }
}
