package com.salesianos.aurareviews.repository;

import com.salesianos.aurareviews.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio para operaciones CRUD de Cliente.
 */
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    
    // Consulta personalizada: Contar clientes por género
    @Query("SELECT c.genero, COUNT(c) FROM Cliente c GROUP BY c.genero")
    List<Object[]> countByGenero();
    
    // Consulta personalizada: Contar clientes por franjas de edad
    @Query(value = "SELECT " +
            "CASE " +
            "WHEN edad BETWEEN 0 AND 15 THEN '0-15' " +
            "WHEN edad BETWEEN 16 AND 24 THEN '16-24' " +
            "WHEN edad BETWEEN 25 AND 35 THEN '25-35' " +
            "WHEN edad BETWEEN 36 AND 50 THEN '36-50' " +
            "ELSE '50+' END as rango, " +
            "COUNT(*) as total " +
            "FROM clientes GROUP BY rango", nativeQuery = true)
    List<Object[]> countByEdadRango();
    
    // Contar clientes con intolerancias
    Long countByIntoleranciaTrue();
}