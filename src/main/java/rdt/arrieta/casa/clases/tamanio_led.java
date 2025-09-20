package rdt.arrieta.casa.clases;

import jakarta.persistence.*;

@Entity
@Table(name = "tamanio_led")
public class tamanio_led {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tamanio_led")
    private Long id;

    @Column
    private String tamanio;

    public Long getId() {
        return id;
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
