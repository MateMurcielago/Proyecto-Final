package rdt.arrieta.casa.clases.embebidas;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class id_inventario_led {
    @Column(name="id_inventario")
    private Long id_inventario;

    @Column(name="id_tamanio_led")
    private Long id_tamanio_led;

    public id_inventario_led() {}

    public id_inventario_led(Long id_inventario, Long id_tamanio_led) {
        this.id_inventario = id_inventario;
        this.id_tamanio_led = id_tamanio_led;
    }

    public Long getId_inventario() {
        return id_inventario;
    }

    public Long getId_tamanio_led() {
        return id_tamanio_led;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if(!(o instanceof id_inventario_led)) return false;
        id_inventario_led that = (id_inventario_led) o;
        if(!Objects.equals(id_inventario, that.id_inventario)) return false;
        return Objects.equals(id_tamanio_led, that.id_tamanio_led);
    }

    @Override
    public int hashCode() {
        int result = id_inventario != null ? id_inventario.hashCode() : 0;
        result = 31 * result + (id_tamanio_led != null ? id_tamanio_led.hashCode() : 0);
        return result;
    }
}
