package com.cristophermartinez.Repuestos.repository;

import com.cristophermartinez.Repuestos.model.Repuesto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepuestoRepository extends JpaRepository<Repuesto, Integer> {
    boolean existsByNombre(String nombre);
}
