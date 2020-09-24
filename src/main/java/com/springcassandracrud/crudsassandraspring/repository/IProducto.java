package com.springcassandracrud.crudsassandraspring.repository;

import com.springcassandracrud.crudsassandraspring.models.entity.Producto;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.List;
import java.util.UUID;

public interface IProducto extends CassandraRepository<Producto, UUID> {
    //Acceder y hacer consultas a la BD

    @AllowFiltering
    List<Producto> findByActivo(Boolean activo);
}
