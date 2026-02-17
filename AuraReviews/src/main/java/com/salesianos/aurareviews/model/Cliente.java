package com.salesianos.aurareviews.model;

import jakarta.persistence.*;

@Entity
@Table(name = "clientes")
public class Cliente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 100)
    private String nombre;
    
    @Column(nullable = false)
    private Integer edad;
    
    @Column(nullable = false, length = 50)
    private String genero;
    
    @Column(nullable = false)
    private Boolean intolerancia = false;
    
    @Column(length = 255)
    private String detalleIntolerancia;
    
    @OneToOne(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private Review review;
    
    public Cliente() {}
    
    public Cliente(String nombre, Integer edad, String genero, Boolean intolerancia, String detalleIntolerancia) {
        this.nombre = nombre;
        this.edad = edad;
        this.genero = genero;
        this.intolerancia = intolerancia;
        this.detalleIntolerancia = detalleIntolerancia;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public Integer getEdad() {
        return edad;
    }
    
    public void setEdad(Integer edad) {
        this.edad = edad;
    }
    
    public String getGenero() {
        return genero;
    }
    
    public void setGenero(String genero) {
        this.genero = genero;
    }
    
    public Boolean getIntolerancia() {
        return intolerancia;
    }
    
    public void setIntolerancia(Boolean intolerancia) {
        this.intolerancia = intolerancia;
    }
    
    public String getDetalleIntolerancia() {
        return detalleIntolerancia;
    }
    
    public void setDetalleIntolerancia(String detalleIntolerancia) {
        this.detalleIntolerancia = detalleIntolerancia;
    }
    
    public Review getReview() {
        return review;
    }
    
    public void setReview(Review review) {
        this.review = review;
    }
}