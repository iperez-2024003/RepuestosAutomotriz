package com.cristophermartinez.Repuestos.repository;

import com.cristophermartinez.Repuestos.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    boolean existsByNombre(String nombre);    // Para validar nombre duplicado
    boolean existsByEmail(String email);      // Para validar email duplicado
}
