package org.MendezGalindoEmiliano.pixup.model;

import org.MendezGalindoEmiliano.pixup.util.ReadUtil;
import org.MendezGalindoEmiliano.pixup.vista.Menu;
import org.MendezGalindoEmiliano.pixup.vista.SolicitaDatos;
import java.util.ArrayList;

public class TipoNotificacion implements SolicitaDatos {
    private Integer id;
    private String descripcion;
    private String ruta;
    private static ArrayList<TipoNotificacion> lista = new ArrayList<>();

    public TipoNotificacion() {
    }

    public TipoNotificacion(Integer id, String descripcion, String ruta) {
        this.id = id;
        this.descripcion = descripcion;
        this.ruta = ruta;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public static void agregarTipoNotificacion(TipoNotificacion tipoNotificacion) {
        lista.add(tipoNotificacion);
    }

    public static ArrayList<TipoNotificacion> getLista() {
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
                    altaTipoNot();
                    break;
                case 2:
                    bajaTipoNoti();
                    break;
                case 3:
                    cambioTipoNoti();
                    break;
                case 4:
                    verTipoNoti();
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

    public void altaTipoNot() {
        Menu.id();
        int nuevoId = ReadUtil.getInstance().leerInt();
        Menu.descripcion();
        String nuevaDescripcion = ReadUtil.getInstance().leer();
        Menu.ruta();
        String nuevaRuta = ReadUtil.getInstance().leer();

        TipoNotificacion nuevoTipoNotificacion = new TipoNotificacion(nuevoId, nuevaDescripcion, nuevaRuta);
        agregarTipoNotificacion(nuevoTipoNotificacion);
        Menu.alta1();
    }

    public void bajaTipoNoti() {
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

    public void cambioTipoNoti() {
        if (lista.isEmpty()) {
            Menu.sinDatos();
            return;
        }

        Menu.id();
        int idCambio = ReadUtil.getInstance().leerInt();

        TipoNotificacion tipoNotificacion = null;
        for (TipoNotificacion t : lista) {
            if (t.getId().equals(idCambio)) {
                tipoNotificacion = t;
                break;
            }
        }

        if (tipoNotificacion == null) {
            Menu.noDatos();
            return;
        }

        Menu.cambio9();
        int opcionCambio = ReadUtil.getInstance().leerInt();

        switch (opcionCambio) {
            case 1:
                Menu.cambio();
                int nuevoId = ReadUtil.getInstance().leerInt();
                tipoNotificacion.setId(nuevoId);
                Menu.cambio1();
                break;
            case 2:
                Menu.cambio();
                String nuevaDescripcion = ReadUtil.getInstance().leer();
                tipoNotificacion.setDescripcion(nuevaDescripcion);
                Menu.cambio1();
                break;
            case 3:
                Menu.cambio();
                String nuevaRuta = ReadUtil.getInstance().leer();
                tipoNotificacion.setRuta(nuevaRuta);
                Menu.cambio1();
                break;
            default:
                Menu.opcionInvalida();
                break;
        }
    }

    public void verTipoNoti() {
        if (lista.isEmpty()) {
            Menu.sinDatos();
        } else {
            System.out.println("Datos:");
            for (TipoNotificacion tipoNotificacion : lista) {
                System.out.println("Id: " + tipoNotificacion.getId());
                System.out.println("Descripcion: " + tipoNotificacion.getDescripcion());
                System.out.println("Ruta: " + tipoNotificacion.getRuta());
                System.out.println("--------------------------------");
            }
        }
    }
}
