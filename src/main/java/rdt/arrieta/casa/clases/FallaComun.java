package rdt.arrieta.casa.clases;

import jakarta.persistence.*;

@Entity
@Table(name = "falla_comun")
public class FallaComun {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_falla_comun")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_modelo_articulo", nullable=false)
    private modelo_articulo articulo;

    @Column
    private String resumen;

    @Column
    private String falla;

    @Column
    private String solucion;

    public Long getId() {
        return id;
    }

    public modelo_articulo getArticulo() {
        return articulo;
    }

    public String getResumen() {
        return resumen;
    }

    public String getFalla() {
        return falla;
    }

    public String getSolucion() {
        return solucion;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setArticulo(modelo_articulo articulo) {
        this.articulo = articulo;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public void setFalla(String falla) {
        this.falla = falla;
    }

    public void setSolucion(String solucion) {
        this.solucion = solucion;
    }
}
