package com.springcassandracrud.crudsassandraspring.repository;

import com.springcassandracrud.crudsassandraspring.models.entity.Cliente;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.UUID;

public interface ICliente extends CassandraRepository<Cliente, UUID> {
}
