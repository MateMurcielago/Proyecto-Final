package rdt.arrieta.casa.clases;

import jakarta.persistence.*;
import rdt.arrieta.casa.clases.embebidas.id_inventario_electronico;

@Entity
@Table(name = "inventario_electronico")
public class Inventario_electronico {
    @EmbeddedId
    private id_inventario_electronico id;

    @ManyToOne
    @MapsId("id_inventario")
    @JoinColumn(name = "id_inventario")
    private Inventario inventario;

    @ManyToOne
    @MapsId("id_codigo_electronico")
    @JoinColumn(name = "id_codigo_electronico")
    private codigo_electronico electronico;

    public id_inventario_electronico getId() {
        return id;
    }

    public Inventario getInventario() {
        return inventario;
    }

    public codigo_electronico getElectronico() {
        return electronico;
    }

    public void setId(id_inventario_electronico id) {
        this.id = id;
    }

    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }

    public void setElectronico(codigo_electronico electronico) {
        this.electronico = electronico;
    }
}
