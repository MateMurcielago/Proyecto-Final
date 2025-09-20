package rdt.arrieta.casa.clases.intermedias;

import jakarta.persistence.*;
import rdt.arrieta.casa.clases.embebidas.ReparacionLedId;
import rdt.arrieta.casa.clases.Reparacion;
import rdt.arrieta.casa.clases.tamanio_led;

@Entity
@Table(name = "reparacion_led")
public class ReparacionLed {
    @EmbeddedId
    private ReparacionLedId id;

    @ManyToOne
    @MapsId("id_reparacion")
    @JoinColumn(name = "id_reparacion")
    private Reparacion reparacion;

    @ManyToOne
    @MapsId("id_tamanio_led")
    @JoinColumn(name = "id_tamanio_led")
    private tamanio_led led;

    public ReparacionLed() {}

    public ReparacionLed(Reparacion reparacion, tamanio_led led) {
        this.reparacion = reparacion;
        this.led = led;
        this.id = new ReparacionLedId(reparacion.getId(), led.getId());
    }

    public ReparacionLedId getId() {
        return id;
    }

    public Reparacion getReparacion() {
        return reparacion;
    }

    public tamanio_led getLed() {
        return led;
    }

    public void setId(ReparacionLedId id) {
        this.id = id;
    }

    public void setReparacion(Reparacion reparacion) {
        this.reparacion = reparacion;
    }

    public void setLed(tamanio_led led) {
        this.led = led;
    }
}
