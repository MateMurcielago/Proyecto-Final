package rdt.arrieta.casa.clases.embebidas;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class id_inventario_electronico {
    @Column(name="id_inventario")
    private Long id_inventario;

    @Column(name="id_codigo_electronico")
    private Long id_codigo_electronico;

    public id_inventario_electronico() {}

    public id_inventario_electronico(Long id_inventario, Long id_codigo_electronico) {
        this.id_inventario = id_inventario;
        this.id_codigo_electronico = id_codigo_electronico;
    }

    public Long getId_inventario() {
        return this.id_inventario;
    }

    public Long getId_codigo_electronico() {
        return this.id_codigo_electronico;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if(!(o instanceof id_inventario_electronico)) return false;
        id_inventario_electronico that = (id_inventario_electronico) o;
        if(!Objects.equals(id_inventario, that.id_inventario)) return false;
        return Objects.equals(id_codigo_electronico, that.id_codigo_electronico);
    }

    @Override
    public int hashCode() {
        int result = id_inventario != null ? id_inventario.hashCode() : 0;
        result = 31 * result + (id_codigo_electronico != null ? id_codigo_electronico.hashCode() : 0);
        return result;
    }
}
