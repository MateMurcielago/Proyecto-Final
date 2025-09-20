package rdt.arrieta.casa.clases;

import jakarta.persistence.*;
import rdt.arrieta.casa.clases.intermedias.ArticuloCliente;
import rdt.arrieta.casa.clases.intermedias.ReparacionElectronico;
import rdt.arrieta.casa.clases.intermedias.ReparacionLed;
import rdt.arrieta.casa.clases.intermedias.ReparacionPantalla;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "reparacion")
public class Reparacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reparacion")
    private Long id;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente"),
            @JoinColumn(name = "id_modelo_articulo", referencedColumnName = "id_modelo_articulo")
    })
    private ArticuloCliente articuloCliente;

    @Column
    private LocalDate fecha_ingreso;

    @Column
    private String codigo;

    @Column
    private String desperfecto;

    @Column
    private String remoto;

    @Column
    private String cable;

    @Column
    private String pilas;

    @Column
    private String otro;

    @Column
    private String estado;

    @Column
    private String trabajo_realizado;

    @Column
    private int horas_mano_obra;

    @Column
    private Double costo;

    @Column
    private String garantia;

    @Column
    private LocalDate fecha_fin;

    @Column
    private LocalDate fecha_retiro;

    @OneToMany(mappedBy = "reparacion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReparacionElectronico> electronicos = new ArrayList<>();

    @OneToMany(mappedBy = "reparacion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReparacionPantalla> pantallas = new ArrayList<>();

    @OneToMany(mappedBy = "reparacion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReparacionLed> leds = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public ArticuloCliente getArticuloCliente() {
        return articuloCliente;
    }

    public LocalDate getFecha_ingreso() {
        return fecha_ingreso;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDesperfecto() {
        return desperfecto;
    }

    public String getRemoto() {
        return remoto;
    }

    public String getCable() {
        return cable;
    }

    public String getPilas() {
        return pilas;
    }

    public String getOtro() {
        return otro;
    }

    public String getEstado() {
        return estado;
    }

    public String getTrabajo_realizado() {
        return trabajo_realizado;
    }

    public int getHoras_mano_obra() {
        return horas_mano_obra;
    }

    public Double getCosto() {
        return costo;
    }

    public String getGarantia() {
        return garantia;
    }

    public LocalDate getFecha_fin() {
        return fecha_fin;
    }

    public LocalDate getFecha_retiro() {
        return fecha_retiro;
    }

    public List<ReparacionElectronico> getElectronicos() {
        return electronicos;
    }

    public List<ReparacionPantalla> getPantallas() {
        return pantallas;
    }

    public List<ReparacionLed> getLeds() {
        return leds;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setArticuloCliente(ArticuloCliente articuloCliente) {
        this.articuloCliente = articuloCliente;
    }

    public void setFecha_ingreso(LocalDate fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setDesperfecto(String desperfecto) {
        this.desperfecto = desperfecto;
    }

    public void setRemoto(String remoto) {
        this.remoto = remoto;
    }

    public void setCable(String cable) {
        this.cable = cable;
    }

    public void setPilas(String pilas) {
        this.pilas = pilas;
    }

    public void setOtro(String otro) {
        this.otro = otro;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setTrabajo_realizado(String trabajo_realizado) {
        this.trabajo_realizado = trabajo_realizado;
    }

    public void setHoras_mano_obra(int horas_mano_obra) {
        this.horas_mano_obra = horas_mano_obra;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public void setGarantia(String garantia) {
        this.garantia = garantia;
    }

    public void setFecha_fin(LocalDate fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public void setFecha_retiro(LocalDate fecha_retiro) {
        this.fecha_retiro = fecha_retiro;
    }

    public void setElectronicos(List<ReparacionElectronico> electronicos) {
        this.electronicos = electronicos;
    }

    public void setPantallas(List<ReparacionPantalla> pantallas) {
        this.pantallas = pantallas;
    }

    public void setLeds(List<ReparacionLed> leds) {
        this.leds = leds;
    }
}
