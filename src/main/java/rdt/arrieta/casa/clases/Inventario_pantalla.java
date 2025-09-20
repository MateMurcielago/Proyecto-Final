package rdt.arrieta.casa.clases;

import jakarta.persistence.*;
import rdt.arrieta.casa.clases.embebidas.id_inventario_pantalla;

@Entity
@Table(name = "inventario_pantalla")
public class Inventario_pantalla {
    @EmbeddedId
    private id_inventario_pantalla id;

    @ManyToOne
    @MapsId("id_inventario")
    @JoinColumn(name = "id_inventario")
    private Inventario inventario;

    @ManyToOne
    @MapsId("id_codigo_pantalla")
    @JoinColumn(name = "id_codigo_pantalla")
    private codigo_pantalla pantalla;

    public id_inventario_pantalla getId() {
        return id;
    }

    public Inventario getInventario() {
        return inventario;
    }

    public codigo_pantalla getPantalla(){
        return pantalla;
    }

    public void setId(id_inventario_pantalla id) {
        this.id = id;
    }

    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }

    public void setPantalla(codigo_pantalla pantalla) {
        this.pantalla = pantalla;
    }
}
