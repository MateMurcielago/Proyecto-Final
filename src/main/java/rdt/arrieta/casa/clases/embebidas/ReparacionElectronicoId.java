package rdt.arrieta.casa.clases.embebidas;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ReparacionElectronicoId implements Serializable {
    @Column(name = "id_reparacion")
    private Long id_reparacion;

    @Column(name = "id_codigo_electronico")
    private Long id_codigo_electronico;

    public ReparacionElectronicoId() {}

    public ReparacionElectronicoId(Long id_reparacion, Long id_codigo_electronico) {
        this.id_reparacion = id_reparacion;
        this.id_codigo_electronico = id_codigo_electronico;
    }

    public Long getId_reparacion() {
        return id_reparacion;
    }

    public Long getId_codigo_electronico() {
        return id_codigo_electronico;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if(!(o instanceof ReparacionElectronicoId)) return false;
        ReparacionElectronicoId that = (ReparacionElectronicoId) o;
        if(!Objects.equals(id_reparacion, that.id_reparacion)) return false;
        return Objects.equals(id_codigo_electronico, that.id_codigo_electronico);
    }

    @Override
    public int hashCode() {
        int result = id_reparacion != null ? id_reparacion.hashCode() : 0;
        result = 31 * result + (id_codigo_electronico != null ? id_codigo_electronico.hashCode() : 0);
        return result;
    }
}
