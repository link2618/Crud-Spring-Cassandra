package com.springcassandracrud.crudsassandraspring.controllers;

import com.springcassandracrud.crudsassandraspring.models.entity.Cliente;
import com.springcassandracrud.crudsassandraspring.services.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

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
    public ResponseEntity<Cliente> crearCliente(@RequestBody Cliente cliente) {
        Date fecha = new Date();
        System.out.println(fecha);
        long lnMilisegundos = fecha.getTime();
        java.sql.Date sqlDate = new java.sql.Date(lnMilisegundos);

        cliente.setCreateAt(sqlDate);
        cliente.setUpdateAt(sqlDate);

        //cliente.setUpdateAt(fecha);
        //cliente.setUpdateAt(fecha);

        return clienteSevicio.crearCliente(cliente);
        //return null;
    }
}
