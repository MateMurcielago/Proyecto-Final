package rdt.arrieta.casa.clases.intermedias;

import jakarta.persistence.*;
import rdt.arrieta.casa.clases.Cliente;
import rdt.arrieta.casa.clases.Reparacion;
import rdt.arrieta.casa.clases.embebidas.ArticuloClienteId;
import rdt.arrieta.casa.clases.modelo_articulo;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "articulo_cliente")
public class ArticuloCliente {
    @EmbeddedId
    private ArticuloClienteId id;

    @ManyToOne
    @MapsId("id_cliente")
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @ManyToOne
    @MapsId("id_modelo_articulo")
    @JoinColumn(name = "id_modelo_articulo")
    private modelo_articulo articulo;

    @OneToMany(mappedBy = "articuloCliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reparacion> reparaciones = new ArrayList<>();

    public ArticuloCliente() {}

    public ArticuloCliente(Cliente cliente, modelo_articulo articulo) {
        this.cliente = cliente;
        this.articulo = articulo;
        this.id = new ArticuloClienteId(cliente.getId(), articulo.getId());
    }

    public ArticuloClienteId getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public modelo_articulo getArticulo() {
        return articulo;
    }

    public List<Reparacion> getReparaciones() {
        return reparaciones;
    }

    public void setId(ArticuloClienteId id) {
        this.id = id;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setArticulo(modelo_articulo articulo) {
        this.articulo = articulo;
    }

    public void setReparaciones(List<Reparacion> reparaciones) {
        this.reparaciones = reparaciones;
    }
}
