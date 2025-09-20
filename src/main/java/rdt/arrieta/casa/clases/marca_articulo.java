package rdt.arrieta.casa.clases;

import jakarta.persistence.*;

@Entity
@Table(name = "marca_articulo")
public class marca_articulo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_marca_articulo")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_tipo_articulo", nullable = false)
    private tipo_articulo tipo;

    @Column
    private String marca;

    public Long getId() {
        return id;
    }

    public tipo_articulo getTipo() {
        return tipo;
    }

    public String getMarca() {
        return marca;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTipo(tipo_articulo tipo) {
        this.tipo = tipo;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
}
