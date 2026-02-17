package com.salesianos.aurareviews.service;

import com.salesianos.aurareviews.model.Cliente;
import com.salesianos.aurareviews.model.Review;
import com.salesianos.aurareviews.repository.ReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReviewServiceTest {
    
    @Mock
    private ReviewRepository reviewRepository;
    
    @InjectMocks
    private ReviewService reviewService;
    
    private Review reviewPrueba;
    private Cliente clientePrueba;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        
        clientePrueba = new Cliente();
        clientePrueba.setId(1L);
        clientePrueba.setNombre("María López");
        
        reviewPrueba = new Review();
        reviewPrueba.setId(1L);
        reviewPrueba.setDescripcion("Excelente servicio");
        reviewPrueba.setValoracion(5);
        reviewPrueba.setCliente(clientePrueba);
    }
    
    @Test
    void testFindById_DebeRetornarReviewCuandoExiste() {
        when(reviewRepository.findById(1L)).thenReturn(Optional.of(reviewPrueba));
        
        Optional<Review> resultado = reviewService.findById(1L);
        
        assertTrue(resultado.isPresent());
        assertEquals("Excelente servicio", resultado.get().getDescripcion());
        assertEquals(5, resultado.get().getValoracion());
        verify(reviewRepository, times(1)).findById(1L);
    }
    
    @Test
    void testDeleteById_DebeEliminarReviewCorrectamente() {
        doNothing().when(reviewRepository).deleteById(1L);
        
        reviewService.deleteById(1L);
        
        verify(reviewRepository, times(1)).deleteById(1L);
    }
}