package com.salesianos.aurareviews.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.salesianos.aurareviews.model.Cliente;
import com.salesianos.aurareviews.model.Review;
import com.salesianos.aurareviews.repository.ClienteRepository;
import com.salesianos.aurareviews.repository.ReviewRepository;
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
class ReviewRestControllerE2ETest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private ReviewRepository reviewRepository;
    
    @Autowired
    private ClienteRepository clienteRepository;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    private Cliente clientePrueba;
    
    @BeforeEach
    void setUp() {
        reviewRepository.deleteAll();
        clienteRepository.deleteAll();
        
        clientePrueba = new Cliente();
        clientePrueba.setNombre("Sara Gómez");
        clientePrueba.setEdad(26);
        clientePrueba.setGenero("Femenino");
        clientePrueba.setIntolerancia(false);
        clientePrueba = clienteRepository.save(clientePrueba);
    }
    
    @Test
    void testCrearReview_DebeRetornarCreatedYReviewGuardada() throws Exception {
        Review nuevaReview = new Review();
        nuevaReview.setDescripcion("Muy satisfecho con el servicio");
        nuevaReview.setValoracion(5);
        nuevaReview.setCliente(clientePrueba);
        
        mockMvc.perform(post("/api/reviews")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(nuevaReview)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.descripcion", is("Muy satisfecho con el servicio")))
                .andExpect(jsonPath("$.valoracion", is(5)))
                .andExpect(jsonPath("$.id").exists());
    }
    
    @Test
    void testEliminarReview_DebeRetornarNoContent() throws Exception {
        Review review = new Review();
        review.setDescripcion("Regular");
        review.setValoracion(3);
        review.setCliente(clientePrueba);
        review = reviewRepository.save(review);
        
        mockMvc.perform(delete("/api/reviews/" + review.getId()))
                .andExpect(status().isNoContent());
    }
}