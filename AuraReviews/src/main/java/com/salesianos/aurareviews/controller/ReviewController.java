package com.salesianos.aurareviews.controller;

import com.salesianos.aurareviews.model.Review;
import com.salesianos.aurareviews.service.ClienteService;
import com.salesianos.aurareviews.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/reviews")
public class ReviewController {
    
    @Autowired
    private ReviewService reviewService;
    
    @Autowired
    private ClienteService clienteService;
    
    
     //Listar todas las reviews.
     
    @GetMapping
    public String listarReviews(Model model) {
        model.addAttribute("reviews", reviewService.findAll());
        return "reviews/lista";
    }
    
    
     //Mostrar formulario para crear una nueva review.
     
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("review", new Review());
        model.addAttribute("clientes", clienteService.findAll());
        return "reviews/formulario";
    }
    
    
     //Guardar una nueva review.
     
    @PostMapping("/guardar")
    public String guardarReview(@ModelAttribute Review review, @RequestParam Long clienteId) {
        clienteService.findById(clienteId).ifPresent(review::setCliente);
        reviewService.save(review);
        return "redirect:/reviews";
    }
    
    
     //Mostrar formulario para editar una review existente.
     
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Optional<Review> review = reviewService.findById(id);
        
        if (review.isPresent()) {
            model.addAttribute("review", review.get());
            model.addAttribute("clientes", clienteService.findAll());
            return "reviews/formulario";
        } else {
            return "redirect:/reviews";
        }
    }
    
    
    //Eliminar una review por su ID.
    @GetMapping("/eliminar/{id}")
    public String eliminarReview(@PathVariable Long id) {
        reviewService.deleteById(id);
        return "redirect:/reviews";
    }
}