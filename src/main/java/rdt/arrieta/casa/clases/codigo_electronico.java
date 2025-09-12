package rdt.arrieta.casa.clases;

import jakarta.persistence.*;

@Entity
@Table(name = "codigo_electronico")
public class codigo_electronico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_codigo_electronico")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_nombre_electronico", nullable=false)
    private nombre_electronico nombre;

    @Column
    private String codigo;

    public Long getId(){
        return id;
    }

    public nombre_electronico getNombre(){
        return nombre;
    }

    public String getCodigo(){
        return codigo;
    }

    public void setId(Long id){
        this.id = id;
    }

    public void setNombre(nombre_electronico nombre){
        this.nombre = nombre;
    }

    public void setCodigo(String codigo){
        this.codigo = codigo;
    }
}
