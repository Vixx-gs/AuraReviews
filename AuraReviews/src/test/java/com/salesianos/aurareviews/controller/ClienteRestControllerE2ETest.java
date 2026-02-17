package com.salesianos.aurareviews.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.salesianos.aurareviews.model.Cliente;
import com.salesianos.aurareviews.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ClienteRestControllerE2ETest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private ClienteRepository clienteRepository;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    @BeforeEach
    void setUp() {
        clienteRepository.deleteAll();
    }
    
    @Test
    void testCrearCliente_DebeRetornarCreatedYClienteGuardado() throws Exception {
        Cliente nuevoCliente = new Cliente();
        nuevoCliente.setNombre("Roberto Díaz");
        nuevoCliente.setEdad(40);
        nuevoCliente.setGenero("Masculino");
        nuevoCliente.setIntolerancia(false);
        
        mockMvc.perform(post("/api/clientes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(nuevoCliente)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre", is("Roberto Díaz")))
                .andExpect(jsonPath("$.edad", is(40)))
                .andExpect(jsonPath("$.id").exists());
    }
    
    @Test
    void testObtenerTodosLosClientes_DebeRetornarListaCompleta() throws Exception {
        Cliente cliente1 = new Cliente();
        cliente1.setNombre("Elena Fernández");
        cliente1.setEdad(32);
        cliente1.setGenero("Femenino");
        cliente1.setIntolerancia(true);
        cliente1.setDetalleIntolerancia("Alergia");
        
        Cliente cliente2 = new Cliente();
        cliente2.setNombre("Miguel Ángel");
        cliente2.setEdad(28);
        cliente2.setGenero("Masculino");
        cliente2.setIntolerancia(false);
        
        clienteRepository.save(cliente1);
        clienteRepository.save(cliente2);
        
        mockMvc.perform(get("/api/clientes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].nombre", is("Elena Fernández")))
                .andExpect(jsonPath("$[1].nombre", is("Miguel Ángel")));
    }
}