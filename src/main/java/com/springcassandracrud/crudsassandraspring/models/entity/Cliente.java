package com.springcassandracrud.crudsassandraspring.models.entity;

import lombok.Data;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.lang.NonNull;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.UUID;

@Table("clientes")
@Data
public class Cliente {

    @PrimaryKey
    private UUID id;
//    @NotBlank(message = "No puede estar vacio el nombre.")
    private String nombre;
    private String apellido;
//    @Email(message = "No tiene un formato valido el email.")
//    @NotBlank(message = "El email no puede estar vacio.")
//    @NonNull
    private String email;
    @Column("create_at")
    private LocalDate createAt;
    @Column("update_at")
    private LocalDate updateAt;

    public Cliente() {
    }

    public Cliente(UUID id, String nombre, String apellido, String email) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
    }

    public Cliente(UUID id, String nombre, String apellido, String email, LocalDate createAt, LocalDate updateAt) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDate createAt) {
        this.createAt = createAt;
    }

    public LocalDate getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDate updateAt) {
        this.updateAt = updateAt;
    }
}
