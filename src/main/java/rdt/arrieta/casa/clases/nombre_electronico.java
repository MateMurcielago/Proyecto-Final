package rdt.arrieta.casa.clases;

import jakarta.persistence.*;

@Entity
@Table(name = "nombre_electronico")
public class nombre_electronico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_nombre_electronico")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_tipo_electronico", nullable=false)
    private tipo_electronico tipo;

    @Column
    private String nombre;

    public Long getId(){
        return id;
    }

    public tipo_electronico getTipo(){
        return tipo;
    }

    public String getNombre(){
        return nombre;
    }

    public void setId(Long id){
        this.id = id;
    }

    public void setTipo(tipo_electronico tipo){
        this.tipo = tipo;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }
}
