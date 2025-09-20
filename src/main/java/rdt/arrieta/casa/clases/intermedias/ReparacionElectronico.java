package rdt.arrieta.casa.clases.intermedias;

import jakarta.persistence.*;
import rdt.arrieta.casa.clases.embebidas.ReparacionElectronicoId;
import rdt.arrieta.casa.clases.Reparacion;
import rdt.arrieta.casa.clases.codigo_electronico;

@Entity
@Table(name = "reparacion_electronico")
public class ReparacionElectronico {
    @EmbeddedId
    private ReparacionElectronicoId id;

    @ManyToOne
    @MapsId("id_reparacion")
    @JoinColumn(name = "id_reparacion")
    private Reparacion reparacion;

    @ManyToOne
    @MapsId("id_codigo_electronico")
    @JoinColumn(name = "id_codigo_electronico")
    private codigo_electronico electronico;

    public ReparacionElectronico() {}

    public ReparacionElectronico(Reparacion reparacion, codigo_electronico electronico) {
        this.reparacion = reparacion;
        this.electronico = electronico;
        this.id = new ReparacionElectronicoId(reparacion.getId(), electronico.getId());
    }

    public ReparacionElectronicoId getId() {
        return id;
    }

    public Reparacion getReparacion() {
        return reparacion;
    }

    public codigo_electronico getElectronico() {
        return electronico;
    }

    public void setId(ReparacionElectronicoId id) {
        this.id = id;
    }

    public void setReparacion(Reparacion reparacion) {
        this.reparacion = reparacion;
    }

    public void setElectronico(codigo_electronico electronico) {
        this.electronico = electronico;
    }
}
