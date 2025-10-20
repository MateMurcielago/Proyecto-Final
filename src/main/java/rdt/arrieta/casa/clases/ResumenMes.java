package rdt.arrieta.casa.clases;

import jakarta.persistence.*;

@Entity
@Table(name = "resumen_mes")
public class ResumenMes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_resumen_mes")
    private Long id;

    @Column
    private int anio;

    @Column
    private int mes;

    @Column
    String descripcion;

    @Column
    Double precio;

    public Long getId() {
        return id;
    }

    public int getAnio() {
        return anio;
    }

    public int getMes() {
        return mes;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
}
