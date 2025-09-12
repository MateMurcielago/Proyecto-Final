package rdt.arrieta.casa.clases;

import jakarta.persistence.*;

@Entity
@Table(name = "tipo_articulo")
public class tipo_articulo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_articulo")
    private Long id;

    @Column
    private String tipo;

    public Long getId(){
        return id;
    }

    public String getTipo(){
        return tipo;
    }

    public void setId(Long id){
        this.id = id;
    }

    public void setTipo(String tipo){
        this.tipo = tipo;
    }
}
