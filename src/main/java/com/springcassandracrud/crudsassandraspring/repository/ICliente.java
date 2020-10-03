package com.springcassandracrud.crudsassandraspring.repository;

import com.springcassandracrud.crudsassandraspring.models.entity.Cliente;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.List;
import java.util.UUID;

public interface ICliente extends CassandraRepository<Cliente, UUID> {
    //Acceder y hacer consultas a la BD

    @AllowFiltering
    List<Cliente> findByEmail(String email);
}
