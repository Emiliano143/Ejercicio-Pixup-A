package org.MendezGalindoEmiliano.pixup.model;

import org.MendezGalindoEmiliano.pixup.util.ReadUtil;
import org.MendezGalindoEmiliano.pixup.vista.Menu;
import org.MendezGalindoEmiliano.pixup.vista.SolicitaDatos;

import java.util.ArrayList;

public class Notificacion implements SolicitaDatos {
    private Integer id;
    private String fechaNotificacion;
    private Usuario usuario;
    private TipoNotificacion tipoNotificacion;
    private static ArrayList<Notificacion> lista = new ArrayList<>();

    public Notificacion() {
    }

    public Notificacion(Integer id, String fechaNotificacion, Usuario usuario, TipoNotificacion tipoNotificacion) {
        this.id = id;
        this.fechaNotificacion = fechaNotificacion;
        this.usuario = usuario;
        this.tipoNotificacion = tipoNotificacion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFechaNotificacion() {
        return fechaNotificacion;
    }

    public void setFechaNotificacion(String fechaNotificacion) {
        this.fechaNotificacion = fechaNotificacion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public TipoNotificacion getTipoNotificacion() {
        return tipoNotificacion;
    }

    public void setTipoNotificacion(TipoNotificacion tipoNotificacion) {
        this.tipoNotificacion = tipoNotificacion;
    }

    public static void agregarNotificacion(Notificacion notificacion) {
        lista.add(notificacion);
    }

    public static ArrayList<Notificacion> getLista() {
        return lista;
    }

    @Override
    public void leerDatos() {
        boolean flag = true;

        while (flag) {
            Menu.principal4();
            int opcion = ReadUtil.getInstance().leerInt();

            switch (opcion) {
                case 1:
                    altaNotificacion();
                    break;
                case 2:
                    bajaNotificacion();
                    break;
                case 3:
                    cambioNotificacion();
                    break;
                case 4:
                    verNotificacion();
                    break;
                case 5:
                    flag = false;
                    break;
                default:
                    Menu.opcionInvalida();
                    break;
            }
        }
    }

    public void altaNotificacion() {
        if (Usuario.getLista().isEmpty() || TipoNotificacion.getLista().isEmpty()) {
            Menu.noDatos();
            return;
        }
        Menu.notificacion();
        Menu.id();
        Integer nuevoId = ReadUtil.getInstance().leerInt();
        Menu.fecha();
        String nuevaFechaNotificacion = ReadUtil.getInstance().leer();

        System.out.println("Seleccione el usuario para la notificacion:");
        ArrayList<Usuario> usuarios = Usuario.getLista();
        for (int i = 0; i < usuarios.size(); i++) {
            System.out.println((i + 1) + ". " + usuarios.get(i).getNombre());
        }
        int usuarioOpc = ReadUtil.getInstance().leerInt();
        if (usuarioOpc < 1 || usuarioOpc > usuarios.size()) {
            Menu.opcionInvalida();
            return;
        }
        Usuario usuarioSeleccionado = usuarios.get(usuarioOpc - 1);

        System.out.println("Seleccione el tipo de notificacion:");
        ArrayList<TipoNotificacion> tipos = TipoNotificacion.getLista();
        for (int i = 0; i < tipos.size(); i++) {
            System.out.println((i + 1) + ". " + tipos.get(i).getDescripcion());
        }
        int tipoOpc = ReadUtil.getInstance().leerInt();
        if (tipoOpc < 1 || tipoOpc > tipos.size()) {
            Menu.opcionInvalida();
            return;
        }
        TipoNotificacion tipoNotificacionSeleccionado = tipos.get(tipoOpc - 1);

        Notificacion nuevaNotificacion = new Notificacion(nuevoId, nuevaFechaNotificacion, usuarioSeleccionado, tipoNotificacionSeleccionado);
        agregarNotificacion(nuevaNotificacion);
        Menu.alta1();
    }

    public void bajaNotificacion() {
        if (lista.isEmpty()) {
            Menu.sinDatos();
            return;
        }

        Menu.id();
        int idBaja = ReadUtil.getInstance().leerInt();

        boolean encontrado = false;
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId().equals(idBaja)) {
                lista.remove(i);
                encontrado = true;
                break;
            }
        }

        if (encontrado) {
            Menu.baja1();
        } else {
            Menu.noDatos();
        }
    }

    public void cambioNotificacion() {
        if (lista.isEmpty()) {
            Menu.sinDatos();
            return;
        }

        Menu.id();
        int idCambio = ReadUtil.getInstance().leerInt();

        Notificacion notificacion = null;
        for (Notificacion n : lista) {
            if (n.getId().equals(idCambio)) {
                notificacion = n;
                break;
            }
        }

        if (notificacion == null) {
            Menu.noDatos();
            return;
        }

        Menu.cambio8();
        int opcionCambio = ReadUtil.getInstance().leerInt();

        switch (opcionCambio) {
            case 1:
                Menu.cambio();
                int nuevoId = ReadUtil.getInstance().leerInt();
                notificacion.setId(nuevoId);
                Menu.cambio1();
                break;
            case 2:
                Menu.cambio();
                String nuevaFecha = ReadUtil.getInstance().leer();
                notificacion.setFechaNotificacion(nuevaFecha);
                Menu.cambio1();
                break;
            default:
                Menu.opcionInvalida();
                break;
        }
    }

    public void verNotificacion() {
        if (lista.isEmpty()) {
            Menu.sinDatos();
        } else {
            System.out.println("Datos:");
            for (Notificacion notificacion : lista) {
                System.out.println("Id: " + notificacion.getId());
                System.out.println("Fecha de Notificacion: " + notificacion.getFechaNotificacion());
                System.out.println("Usuario: " + notificacion.getUsuario().getNombre() + " ID: " + notificacion.getUsuario().getId());
                System.out.println("Tipo de Notificacion: " + " ID: " + notificacion.getTipoNotificacion().getId());
                System.out.println("--------------------------------");
            }
        }
    }
}
