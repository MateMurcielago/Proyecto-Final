package rdt.arrieta.casa.clases;

import jakarta.persistence.*;
import rdt.arrieta.casa.clases.intermedias.ArticuloCliente;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Long id;

    @Column
    private String nombre;

    @Column
    private String apellido;

    @Column
    private Long numero_telefono;

    @Column
    private String calle;

    @Column
    private String numero_casa;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ArticuloCliente> articulos = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public Long getNumero_telefono() {
        return numero_telefono;
    }

    public String getCalle() {
        return calle;
    }

    public String getNumero_casa() {
        return numero_casa;
    }

    public List<ArticuloCliente> getArticulos() {
        return articulos;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setNumero_telefono(Long numero_telefono) {
        this.numero_telefono = numero_telefono;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public void setNumero_casa(String numero_casa) {
        this.numero_casa = numero_casa;
    }

    public void setArticulos(List<ArticuloCliente> articulos) {
        this.articulos = articulos;
    }
}
