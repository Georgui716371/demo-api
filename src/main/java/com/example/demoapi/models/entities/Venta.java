package com.example.api_rest.models.entities;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name ="ventas")
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String estado;
    @Column(name="fecha_venta")
    @Temporal(TemporalType.DATE)
    private Date fechaVenta;

    @JsonIgnoreProperties(value = {"hibernateLazyInitializer","handler"},allowSetters = true)
    @ManyToOne(fetch=FetchType.LAZY)
    private Cliente  cliente;


    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    @OneToMany(fetch =FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="venta_id")
    private List<DetalleVenta> items;

    public Venta(){
        items = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<DetalleVenta> getItems() {
        return items;
    }

    public void setItems(List<DetalleVenta> items) {
        this.items = items;
    }

    @PrePersist
    public void prePersist(){
        this.fechaVenta=new Date();
    }


}
