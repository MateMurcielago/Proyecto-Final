package rdt.arrieta.casa.clases;

import jakarta.persistence.*;
import rdt.arrieta.casa.clases.embebidas.id_inventario_led;

@Entity
@Table(name = "inventario_led")
public class Inventario_led {
    @EmbeddedId
    private id_inventario_led id;

    @ManyToOne
    @MapsId("id_inventario")
    @JoinColumn(name = "id_inventario")
    private Inventario inventario;

    @ManyToOne
    @MapsId("id_tamanio_led")
    @JoinColumn(name = "id_tamanio_led")
    private tamanio_led led;

    public id_inventario_led getId() {
        return id;
    }

    public Inventario getInventario() {
        return inventario;
    }

    public tamanio_led getLed() {
        return led;
    }

    public void setId(id_inventario_led id) {
        this.id = id;
    }

    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }

    public void setLed(tamanio_led led) {
        this.led = led;
    }
}
