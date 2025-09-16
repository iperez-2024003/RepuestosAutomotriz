package com.cristophermartinez.Repuestos.controller;

import com.cristophermartinez.Repuestos.model.Repuesto;
import com.cristophermartinez.Repuestos.repository.RepuestoRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/repuestos")
public class RepuestoController {

    private final RepuestoRepository repuestoRepository;

    public RepuestoController(RepuestoRepository repuestoRepository) {
        this.repuestoRepository = repuestoRepository;
    }

    @GetMapping
    public List<Repuesto> getAllRepuestos() {
        return repuestoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Repuesto> getRepuestoById(@PathVariable Integer id) {
        Optional<Repuesto> repuesto = repuestoRepository.findById(id);
        return repuesto.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Repuesto> createRepuesto(@RequestBody Repuesto repuesto) {
        Repuesto newRepuesto = repuestoRepository.save(repuesto);
        return ResponseEntity.ok(newRepuesto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Repuesto> updateRepuesto(@PathVariable Integer id, @RequestBody Repuesto repuesto) {
        if (!repuestoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repuesto.setId(id);
        Repuesto updatedRepuesto = repuestoRepository.save(repuesto);
        return ResponseEntity.ok(updatedRepuesto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRepuesto(@PathVariable Integer id) {
        if (!repuestoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repuestoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
