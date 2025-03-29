package org.MendezGalindoEmiliano.pixup.model;

import org.MendezGalindoEmiliano.pixup.vista.SolicitaDatos;

public class Usuario implements SolicitaDatos {
    private Integer id;
    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    private String contrasena;
    private String email;
    private String rfc;

    @Override
    public void leerDatos() {

    }
}
