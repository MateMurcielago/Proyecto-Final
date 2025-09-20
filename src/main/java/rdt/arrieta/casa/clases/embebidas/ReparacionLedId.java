package rdt.arrieta.casa.clases.embebidas;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ReparacionLedId implements Serializable {
    @Column (name = "id_reparacion")
    private Long id_reparacion;

    @Column (name = "id_tamanio_led")
    private Long id_tamanio_led;

    public ReparacionLedId() {}

    public ReparacionLedId(Long id_reparacion, Long id_tamanio_led) {
        this.id_reparacion = id_reparacion;
        this.id_tamanio_led = id_tamanio_led;
    }

    public Long getId_reparacion() {
        return id_reparacion;
    }

    public Long getId_tamanio_led() {
        return id_tamanio_led;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if(!(o instanceof ReparacionLedId)) return false;
        ReparacionLedId that = (ReparacionLedId) o;
        if(!Objects.equals(id_reparacion, that.id_reparacion)) return false;
        return Objects.equals(id_tamanio_led, that.id_tamanio_led);
    }

    @Override
    public int hashCode() {
        int result = id_reparacion != null ? id_reparacion.hashCode() : 0;
        result = 31 * result + (id_tamanio_led != null ? id_tamanio_led.hashCode() : 0);
        return result;
    }
}
