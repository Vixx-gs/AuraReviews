package com.salesianos.aurareviews.model;

import jakarta.persistence.*;

@Entity
@Table(name = "reviews")
public class Review {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 500)
    private String descripcion;
    
    @Column(nullable = false)
    private Integer valoracion;
    
    @OneToOne
    @JoinColumn(name = "id_cliente", unique = true)
    private Cliente cliente;
    
    public Review() {}
    
    public Review(String descripcion, Integer valoracion, Cliente cliente) {
        this.descripcion = descripcion;
        this.valoracion = valoracion;
        this.cliente = cliente;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public Integer getValoracion() {
        return valoracion;
    }
    
    public void setValoracion(Integer valoracion) {
        this.valoracion = valoracion;
    }
    
    public Cliente getCliente() {
        return cliente;
    }
    
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}