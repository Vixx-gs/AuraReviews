package com.salesianos.aurareviews.repository;

import com.salesianos.aurareviews.model.Cliente;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ClienteRepositoryIntegrationTest {
    
    @Autowired
    private TestEntityManager entityManager;
    
    @Autowired
    private ClienteRepository clienteRepository;
    
    @Test
    void testGuardarYRecuperarCliente() {
        Cliente cliente = new Cliente();
        cliente.setNombre("Ana García");
        cliente.setEdad(28);
        cliente.setGenero("Femenino");
        cliente.setIntolerancia(true);
        cliente.setDetalleIntolerancia("Intolerancia a la lactosa");
        
        entityManager.persist(cliente);
        entityManager.flush();
        
        Optional<Cliente> clienteRecuperado = clienteRepository.findById(cliente.getId());
        
        assertTrue(clienteRecuperado.isPresent());
        assertEquals("Ana García", clienteRecuperado.get().getNombre());
        assertEquals(28, clienteRecuperado.get().getEdad());
        assertTrue(clienteRecuperado.get().getIntolerancia());
    }
    
    @Test
    void testContarClientesConIntolerancias() {
        Cliente cliente1 = new Cliente();
        cliente1.setNombre("Pedro Ruiz");
        cliente1.setEdad(35);
        cliente1.setGenero("Masculino");
        cliente1.setIntolerancia(true);
        cliente1.setDetalleIntolerancia("Celiaco");
        
        Cliente cliente2 = new Cliente();
        cliente2.setNombre("Laura Martín");
        cliente2.setEdad(42);
        cliente2.setGenero("Femenino");
        cliente2.setIntolerancia(false);
        
        entityManager.persist(cliente1);
        entityManager.persist(cliente2);
        entityManager.flush();
        
        Long cantidad = clienteRepository.countByIntoleranciaTrue();
        
        assertEquals(1, cantidad);
    }
}