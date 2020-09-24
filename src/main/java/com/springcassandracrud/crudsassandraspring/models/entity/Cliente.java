package com.springcassandracrud.crudsassandraspring.models.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.Date;
import java.util.UUID;

@Table("clientes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    @PrimaryKey
    private UUID id;
    private String nombre;
    private String apellido;
    private String email;
    @Column("create_at")
    private Date createAt;
}
