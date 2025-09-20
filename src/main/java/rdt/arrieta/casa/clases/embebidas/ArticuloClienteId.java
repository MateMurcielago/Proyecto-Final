package rdt.arrieta.casa.clases.embebidas;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ArticuloClienteId implements Serializable {
    @Column (name = "id_cliente")
    private Long id_cliente;

    @Column (name = "id_modelo_articulo")
    private Long id_modelo_articulo;

    public ArticuloClienteId() {}

    public ArticuloClienteId(Long id_cliente, Long id_modelo_articulo) {
        this.id_cliente = id_cliente;
        this.id_modelo_articulo = id_modelo_articulo;
    }

    public Long getId_cliente() {
        return id_cliente;
    }

    public Long getId_modelo_articulo() {
        return id_modelo_articulo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if(!(o instanceof ArticuloClienteId)) return false;
        ArticuloClienteId that = (ArticuloClienteId) o;
        if(!Objects.equals(id_cliente, that.id_cliente)) return false;
        return Objects.equals(id_modelo_articulo, that.id_modelo_articulo);
    }

    @Override
    public int hashCode() {
        int result = id_cliente != null ? id_cliente.hashCode() : 0;
        result = 31 * result + (id_modelo_articulo != null ? id_modelo_articulo.hashCode() : 0);
        return result;
    }
}
