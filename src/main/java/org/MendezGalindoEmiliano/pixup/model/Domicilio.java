package org.MendezGalindoEmiliano.pixup.model;

import org.MendezGalindoEmiliano.pixup.vista.SolicitaDatos;

public class Domicilio implements SolicitaDatos {
    private Integer id;
    private String calle;
    private String numeroExt;
    private String numeroInt;
    private Domicilio domicilio;
    private Colonia colonia;
    private Usuario usuario;

    public Domicilio() {
    }

    public Domicilio(Integer id, Usuario usuario, Colonia colonia, Domicilio domicilio, String numeroExt, String numeroInt, String calle) {
        this.id = id;
        this.usuario = usuario;
        this.colonia = colonia;
        this.domicilio = domicilio;
        this.numeroExt = numeroExt;
        this.numeroInt = numeroInt;
        this.calle = calle;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Colonia getColonia() {
        return colonia;
    }

    public void setColonia(Colonia colonia) {
        this.colonia = colonia;
    }

    public Domicilio getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }

    public String getNumeroInt() {
        return numeroInt;
    }

    public void setNumeroInt(String numeroInt) {
        this.numeroInt = numeroInt;
    }

    public String getNumeroExt() {
        return numeroExt;
    }

    public void setNumeroExt(String numeroExt) {
        this.numeroExt = numeroExt;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    @Override
    public void leerDatos() {

    }
}


