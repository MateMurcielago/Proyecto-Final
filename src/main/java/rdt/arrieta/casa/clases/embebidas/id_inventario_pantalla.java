package rdt.arrieta.casa.clases.embebidas;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class id_inventario_pantalla {
    @Column(name = "id_inventario")
    private Long id_inventario;

    @Column(name = "id_codigo_pantalla")
    private Long id_codigo_pantalla;

    public id_inventario_pantalla() {};

    public id_inventario_pantalla(Long id_inventario, Long id_codigo_pantalla) {
        this.id_inventario = id_inventario;
        this.id_codigo_pantalla = id_codigo_pantalla;
    }

    public Long getId_inventario() {
        return id_inventario;
    }

    public Long getId_codigo_pantalla() {
        return id_codigo_pantalla;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if(!(o instanceof id_inventario_pantalla)) return false;
        id_inventario_pantalla that = (id_inventario_pantalla) o;
        if(!Objects.equals(id_inventario, that.id_inventario)) return false;
        return Objects.equals(id_codigo_pantalla, that.id_codigo_pantalla);
    }

    @Override
    public int hashCode() {
        int result = id_inventario != null ? id_inventario.hashCode() : 0;
        result = 31 * result + (id_codigo_pantalla != null ? id_codigo_pantalla.hashCode() : 0);
        return result;
    }
}
