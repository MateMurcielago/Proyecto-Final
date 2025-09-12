package rdt.arrieta.casa.clases;

import jakarta.persistence.*;

@Entity
@Table(name = "tipo_electronico")
public class tipo_electronico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_electronico")
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
