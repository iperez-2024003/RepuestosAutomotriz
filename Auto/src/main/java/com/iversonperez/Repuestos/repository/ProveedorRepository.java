package com.cristophermartinez.Repuestos.repository;

import com.cristophermartinez.Repuestos.model.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Integer> {
    boolean existsByNombre(String nombre);
    boolean existsByEmail(String email);
}
