package com.salesianos.aurareviews.service;

import com.salesianos.aurareviews.model.Review;
import com.salesianos.aurareviews.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ReviewService {
    
    @Autowired
    private ReviewRepository reviewRepository;

    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

    public Optional<Review> findById(Long id) {
        return reviewRepository.findById(id);
    }

    public Review save(Review review) {
        return reviewRepository.save(review);
    }

    public void deleteById(Long id) {
        reviewRepository.deleteById(id);
    }

    public Map<Integer, Long> getReviewsPorValoracion() {
        List<Object[]> resultados = reviewRepository.countByValoracion();
        Map<Integer, Long> datos = new HashMap<>();
        
        // Inicializar todas las valoraciones de 1 a 5 con 0
        for (int i = 1; i <= 5; i++) {
            datos.put(i, 0L);
        }
        
        // Llenar con los datos reales de la base de datos
        for (Object[] resultado : resultados) {
            Integer valoracion = (Integer) resultado[0];
            Long cantidad = (Long) resultado[1];
            datos.put(valoracion, cantidad);
        }
        
        return datos;
    }
}