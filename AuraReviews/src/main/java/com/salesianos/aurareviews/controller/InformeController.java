package com.salesianos.aurareviews.controller;

import com.salesianos.aurareviews.service.ClienteService;
import com.salesianos.aurareviews.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/informe")
public class InformeController {
    
    @Autowired
    private ClienteService clienteService;
    
    @Autowired
    private ReviewService reviewService;
    

     //Mostrar la página de informes con todos los KPIs.

    @GetMapping
    public String mostrarInforme(Model model) {
        // Datos para gráficos
        Map<String, Long> clientesPorGenero = clienteService.getClientesPorGenero();
        Map<Integer, Long> reviewsPorValoracion = reviewService.getReviewsPorValoracion();
        Map<String, Long> clientesPorEdad = clienteService.getClientesPorEdad();
        Long clientesConIntolerancias = clienteService.contarClientesConIntolerancias();
        
        // Agregar al modelo
        model.addAttribute("clientesPorGenero", clientesPorGenero);
        model.addAttribute("reviewsPorValoracion", reviewsPorValoracion);
        model.addAttribute("clientesPorEdad", clientesPorEdad);
        model.addAttribute("clientesConIntolerancias", clientesConIntolerancias);
        
        return "informe/dashboard";
    }
}