package rdt.arrieta.casa.clases.intermedias;

import jakarta.persistence.*;
import rdt.arrieta.casa.clases.embebidas.ReparacionPantallaId;
import rdt.arrieta.casa.clases.Reparacion;
import rdt.arrieta.casa.clases.codigo_pantalla;

@Entity
@Table(name = "reparacion_pantalla")
public class ReparacionPantalla {
    @EmbeddedId
    private ReparacionPantallaId id;

    @ManyToOne
    @MapsId("id_reparacion")
    @JoinColumn(name = "id_reparacion")
    private Reparacion reparacion;

    @ManyToOne
    @MapsId("id_codigo_pantalla")
    @JoinColumn(name = "id_codigo_pantalla")
    private codigo_pantalla pantalla;

    public ReparacionPantalla() {}

    public ReparacionPantalla(Reparacion reparacion, codigo_pantalla pantalla) {
        this.reparacion = reparacion;
        this.pantalla = pantalla;
        this.id = new ReparacionPantallaId(reparacion.getId(), pantalla.getId());
    }

    public ReparacionPantallaId getId() {
        return id;
    }

    public Reparacion getReparacion() {
        return reparacion;
    }

    public codigo_pantalla getPantalla() {
        return pantalla;
    }

    public void setId(ReparacionPantallaId id) {
        this.id = id;
    }

    public void setReparacion(Reparacion reparacion) {
        this.reparacion = reparacion;
    }

    public void setPantalla(codigo_pantalla pantalla) {
        this.pantalla = pantalla;
    }
}
