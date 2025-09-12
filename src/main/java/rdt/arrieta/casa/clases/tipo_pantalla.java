package rdt.arrieta.casa.clases;

import jakarta.persistence.*;

@Entity
@Table(name = "tipo_pantalla")
public class tipo_pantalla {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_pantalla")
    private Long id;

    @Column
    String tipo;

    public Long getId() {
        return this.id;
    }

    public String getTipo() {
        return this.tipo;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
