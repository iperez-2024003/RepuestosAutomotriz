package com.cristophermartinez.Repuestos.Validacion;

import com.cristophermartinez.Repuestos.model.Cliente;
import com.cristophermartinez.Repuestos.repository.ClienteRepository;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente crearCliente(Cliente cliente) {
        if (clienteRepository.existsByNombre(cliente.getNombre())) {
            throw new IllegalArgumentException(" El nombre ya está registrado.");
        }
        if (clienteRepository.existsByEmail(cliente.getEmail())) {
            throw new IllegalArgumentException(" El correo ya está registrado.");
        }
        return clienteRepository.save(cliente);
    }

    public Cliente actualizarCliente(Integer id, Cliente cliente) {
        return clienteRepository.findById(id).map(existing -> {
            if (clienteRepository.existsByNombre(cliente.getNombre()) &&
                    !existing.getNombre().equals(cliente.getNombre())) {
                throw new IllegalArgumentException(" El nombre ya está en uso.");
            }

            if (clienteRepository.existsByEmail(cliente.getEmail()) &&
                    !existing.getEmail().equals(cliente.getEmail())) {
                throw new IllegalArgumentException(" El correo ya está en uso.");
            }

            cliente.setId(id);
            return clienteRepository.save(cliente);
        }).orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado con id " + id));
    }
}
