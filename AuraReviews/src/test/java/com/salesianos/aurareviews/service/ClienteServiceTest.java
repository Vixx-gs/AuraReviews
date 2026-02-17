package com.salesianos.aurareviews.service;

import com.salesianos.aurareviews.model.Cliente;
import com.salesianos.aurareviews.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClienteServiceTest {
    
    @Mock
    private ClienteRepository clienteRepository;
    
    @InjectMocks
    private ClienteService clienteService;
    
    private Cliente clientePrueba;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        
        clientePrueba = new Cliente();
        clientePrueba.setId(1L);
        clientePrueba.setNombre("Juan Pérez");
        clientePrueba.setEdad(30);
        clientePrueba.setGenero("Masculino");
        clientePrueba.setIntolerancia(false);
    }
    
    @Test
    void testFindAll_DebeRetornarListaDeClientes() {
        List<Cliente> clientesEsperados = Arrays.asList(clientePrueba);
        when(clienteRepository.findAll()).thenReturn(clientesEsperados);
        
        List<Cliente> resultado = clienteService.findAll();
        
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Juan Pérez", resultado.get(0).getNombre());
        verify(clienteRepository, times(1)).findAll();
    }
    
    @Test
    void testSave_DebeGuardarClienteCorrectamente() {
        when(clienteRepository.save(any(Cliente.class))).thenReturn(clientePrueba);
        
        Cliente resultado = clienteService.save(clientePrueba);
        
        assertNotNull(resultado);
        assertEquals("Juan Pérez", resultado.getNombre());
        assertEquals(30, resultado.getEdad());
        verify(clienteRepository, times(1)).save(clientePrueba);
    }
}