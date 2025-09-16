package com.cristophermartinez.Repuestos.Validacion;

import com.cristophermartinez.Repuestos.model.Proveedor;
import com.cristophermartinez.Repuestos.repository.ProveedorRepository;
import org.springframework.stereotype.Service;

@Service
public class ProveedorService {

    private final ProveedorRepository proveedorRepository;

    public ProveedorService(ProveedorRepository proveedorRepository) {
        this.proveedorRepository = proveedorRepository;
    }

    public Proveedor crearProveedor(Proveedor proveedor) {
        if (proveedorRepository.existsByNombre(proveedor.getNombre())) {
            throw new IllegalArgumentException(" El nombre ya está registrado.");
        }
        if (proveedorRepository.existsByEmail(proveedor.getEmail())) {
            throw new IllegalArgumentException(" El correo ya está registrado.");
        }
        return proveedorRepository.save(proveedor);
    }

    public Proveedor actualizarProveedor(Integer id, Proveedor proveedor) {
        return proveedorRepository.findById(id).map(existing -> {
            if (proveedorRepository.existsByNombre(proveedor.getNombre()) &&
                    !existing.getNombre().equals(proveedor.getNombre())) {
                throw new IllegalArgumentException("El nombre ya está en uso.");
            }

            if (proveedorRepository.existsByEmail(proveedor.getEmail()) &&
                    !existing.getEmail().equals(proveedor.getEmail())) {
                throw new IllegalArgumentException(" El correo ya está en uso.");
            }

            proveedor.setId(id);
            return proveedorRepository.save(proveedor);
        }).orElseThrow(() -> new IllegalArgumentException("Proveedor no encontrado con id " + id));
    }
}
