package org.MendezGalindoEmiliano.pixup.model;

import org.MendezGalindoEmiliano.pixup.vista.SolicitaDatos;

public class Notificacion implements SolicitaDatos {
    private Integer id;
    private String fechaNotificacion;
    private Usuario usuario;
    private TipoNotificacion tipoNotificacion;

    @Override
    public void leerDatos() {

    }
}
