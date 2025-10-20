package rdt.arrieta.casa.clases;

import jakarta.persistence.*;

@Entity
@Table(name = "resumen_mes_reparacion")
public class ResumenMesReparacion {
    @Id
    @Column(name = "id_resumen_mes")
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id_resumen_mes", nullable=false)
    private ResumenMes detalle;

    public Long getId() {
        return id;
    }

    public ResumenMes getDetalle() {
        return detalle;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDetalle(ResumenMes detalle) {
        this.detalle = detalle;
    }
}
