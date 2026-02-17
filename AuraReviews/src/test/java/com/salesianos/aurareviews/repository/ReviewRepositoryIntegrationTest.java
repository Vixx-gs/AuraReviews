package com.salesianos.aurareviews.repository;

import com.salesianos.aurareviews.model.Cliente;
import com.salesianos.aurareviews.model.Review;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ReviewRepositoryIntegrationTest {
    
    @Autowired
    private TestEntityManager entityManager;
    
    @Autowired
    private ReviewRepository reviewRepository;
    
    @Test
    void testGuardarYRecuperarReview() {
        Cliente cliente = new Cliente();
        cliente.setNombre("Carlos Sánchez");
        cliente.setEdad(30);
        cliente.setGenero("Masculino");
        cliente.setIntolerancia(false);
        entityManager.persist(cliente);
        
        Review review = new Review();
        review.setDescripcion("Muy buen servicio");
        review.setValoracion(4);
        review.setCliente(cliente);
        
        entityManager.persist(review);
        entityManager.flush();
        
        List<Review> reviews = reviewRepository.findAll();
        
        assertFalse(reviews.isEmpty());
        assertEquals("Muy buen servicio", reviews.get(0).getDescripcion());
        assertEquals(4, reviews.get(0).getValoracion());
    }
    
    @Test
    void testCountByValoracion() {
        Cliente cliente1 = new Cliente();
        cliente1.setNombre("Isabel Torres");
        cliente1.setEdad(25);
        cliente1.setGenero("Femenino");
        cliente1.setIntolerancia(false);
        entityManager.persist(cliente1);
        
        Cliente cliente2 = new Cliente();
        cliente2.setNombre("Pedro Gómez");
        cliente2.setEdad(30);
        cliente2.setGenero("Masculino");
        cliente2.setIntolerancia(false);
        entityManager.persist(cliente2);
        
        Review review1 = new Review();
        review1.setDescripcion("Excelente");
        review1.setValoracion(5);
        review1.setCliente(cliente1);
        
        Review review2 = new Review();
        review2.setDescripcion("Muy bueno");
        review2.setValoracion(5);
        review2.setCliente(cliente2);
        
        entityManager.persist(review1);
        entityManager.persist(review2);
        entityManager.flush();
        
        List<Object[]> resultados = reviewRepository.countByValoracion();
        
        assertFalse(resultados.isEmpty());
    }
}