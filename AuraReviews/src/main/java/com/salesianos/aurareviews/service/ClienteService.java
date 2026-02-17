package com.salesianos.aurareviews.service;

import com.salesianos.aurareviews.model.Cliente;
import com.salesianos.aurareviews.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Servicio que gestiona la lógica de negocio para Cliente.
 */
@Service
public class ClienteService {
    
    @Autowired
    private ClienteRepository clienteRepository;
    
    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }
    
    public Optional<Cliente> findById(Long id) {
        return clienteRepository.findById(id);
    }

    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public void deleteById(Long id) {
        clienteRepository.deleteById(id);
    }

    public Map<String, Long> getClientesPorGenero() {
        List<Object[]> resultados = clienteRepository.countByGenero();
        Map<String, Long> datos = new HashMap<>();
        
        for (Object[] resultado : resultados) {
            String genero = (String) resultado[0];
            Long cantidad = (Long) resultado[1];
            datos.put(genero, cantidad);
        }
        
        return datos;
    }
    
    public Map<String, Long> getClientesPorEdad() {
        List<Object[]> resultados = clienteRepository.countByEdadRango();
        Map<String, Long> datos = new HashMap<>();
        
        for (Object[] resultado : resultados) {
            String rango = (String) resultado[0];
            Long cantidad = ((Number) resultado[1]).longValue();
            datos.put(rango, cantidad);
        }
        
        return datos;
    }
    
    public Long contarClientesConIntolerancias() {
        return clienteRepository.countByIntoleranciaTrue();
    }
}