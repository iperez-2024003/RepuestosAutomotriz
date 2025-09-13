package com.cristophermartinez.Repuestos.controller;

import com.cristophermartinez.Repuestos.model.Proveedor;
import com.cristophermartinez.Repuestos.Validacion.ProveedorService;
import com.cristophermartinez.Repuestos.repository.ProveedorRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/proveedores")
public class ProveedorController {

    private final ProveedorRepository proveedorRepository;
    private final ProveedorService proveedorService;

    public ProveedorController(ProveedorRepository proveedorRepository, ProveedorService proveedorService) {
        this.proveedorRepository = proveedorRepository;
        this.proveedorService = proveedorService;
    }

    @GetMapping
    public List<Proveedor> getAllProveedores() {
        return proveedorRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Proveedor> getProveedorById(@PathVariable Integer id) {
        Optional<Proveedor> proveedor = proveedorRepository.findById(id);
        return proveedor.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createProveedor(@RequestBody Proveedor proveedor) {
        try {
            return ResponseEntity.ok(proveedorService.crearProveedor(proveedor));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProveedor(@PathVariable Integer id, @RequestBody Proveedor proveedor) {
        try {
            return ResponseEntity.ok(proveedorService.actualizarProveedor(id, proveedor));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProveedor(@PathVariable Integer id) {
        if (!proveedorRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        proveedorRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
