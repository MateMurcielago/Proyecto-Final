package rdt.arrieta.casa.clases;

import jakarta.persistence.*;

@Entity
@Table(name = "codigo_pantalla")
public class codigo_pantalla {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_codigo_pantalla")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_tamanio_pantalla")
    private tamanio_pantalla tamanio;

    @ManyToOne
    @JoinColumn(name = "id_tipo_pantalla")
    private tipo_pantalla tipo;

    @Column
    private String codigo;

    public Long getId() {
        return id;
    }

    public tamanio_pantalla getTamanio() {
        return tamanio;
    }

    public tipo_pantalla getTipo() {
        return tipo;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTamanio(tamanio_pantalla tamanio) {
        this.tamanio = tamanio;
    }

    public void setTipo(tipo_pantalla tipo) {
        this.tipo = tipo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
