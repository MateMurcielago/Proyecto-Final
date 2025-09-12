package rdt.arrieta.casa.clases;

import jakarta.persistence.*;

@Entity
@Table(name = "modelo_articulo")
public class modelo_articulo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_modelo_articulo")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_marca_articulo", nullable=false)
    private marca_articulo marca;

    @Column
    private String modelo;

    public Long getId(){
        return id;
    }

    public marca_articulo getMarca(){
        return marca;
    }

    public String getModelo(){
        return modelo;
    }

    public void setId(Long id){
        this.id = id;
    }

    public void setMarca(marca_articulo marca){
        this.marca = marca;
    }

    public void setModelo(String modelo){
        this.modelo = modelo;
    }
}
