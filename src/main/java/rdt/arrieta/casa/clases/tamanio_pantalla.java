package rdt.arrieta.casa.clases;

import jakarta.persistence.*;

@Entity
@Table(name = "tamanio_pantalla")
public class tamanio_pantalla {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tamanio_pantalla")
    private Long id;

    @Column
    String tamanio;

    public Long getId() {
        return this.id;
    }

    public String getTamanio() {
        return tamanio;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTamanio(String tamanio) {
        this.tamanio = tamanio;
    }
}
