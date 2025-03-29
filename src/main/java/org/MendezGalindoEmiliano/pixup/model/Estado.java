package org.MendezGalindoEmiliano.pixup.model;

import org.MendezGalindoEmiliano.pixup.util.ReadUtil;
import org.MendezGalindoEmiliano.pixup.vista.Menu;
import org.MendezGalindoEmiliano.pixup.vista.SolicitaDatos;

import java.util.ArrayList;

public class Estado implements SolicitaDatos {
    private Integer id;
    private String nombre;
    public static ArrayList<Estado> lista = new ArrayList<>();

    public Estado() {
    }

    public Estado(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void agregarEstado(Estado estado) {
        lista.add(estado);
    }

    public static ArrayList<Estado> getLista() {
        return lista;
    }

    public void setLista(ArrayList<Estado> lista) {
        this.lista = lista;
    }

    @Override
    public void leerDatos() {
        boolean flag = true;
        int opcion;

        while (flag) {
            Menu.principal4();
            opcion = ReadUtil.getInstance().leerInt();

            switch (opcion) {
                case 1:
                    altaEstado();
                    break;
                case 2:
                    bajaEstado();
                    break;
                case 3:
                    cambioEstado();
                    break;
                case 4:
                    verEstado();
                    break;
                case 5:
                    flag = false;
                    break;
                default:
                    Menu.opcionInvalida();
            }
        }
    }

    public void altaEstado() {
        Menu.estado();
        Menu.id();
        Integer nuevoId = ReadUtil.getInstance().leerInt();
        Menu.nombre();
        String nuevoNombre = ReadUtil.getInstance().leer();

        Estado nuevoEstado = new Estado(nuevoId, nuevoNombre);
        agregarEstado(nuevoEstado);
        Menu.alta1();
    }

    public void bajaEstado() {
        if (lista.isEmpty()) {
            Menu.sinDatos();
            return;
        }

        Menu.id();
        int idBaja = ReadUtil.getInstance().leerInt();

        boolean encontrado = false;
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId() == idBaja) {
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

    public void cambioEstado() {
        if (lista.isEmpty()) {
            Menu.sinDatos();
            return;
        }

        Menu.id();
        int idCambio = ReadUtil.getInstance().leerInt();

        Estado estado = null;
        for (Estado e : lista) {
            if (e.getId() == idCambio) {
                estado = e;
                break;
            }
        }

        if (estado == null) {
            Menu.noDatos();
            return;
        }

        Menu.cambio2();
        int opcionCambio = ReadUtil.getInstance().leerInt();

        switch (opcionCambio) {
            case 1:
                Menu.cambio();
                int nuevoId = ReadUtil.getInstance().leerInt();
                estado.setId(nuevoId);
                Menu.cambio1();
                break;
            case 2:
                Menu.cambio();
                String nuevoNombre = ReadUtil.getInstance().leer();
                estado.setNombre(nuevoNombre);
                Menu.cambio1();
                break;
            default:
                Menu.opcionInvalida();
                break;
        }
    }

    public void verEstado() {
        if (lista.isEmpty()) {
            Menu.sinDatos();
        } else {
            System.out.println("Datos:");
            for (int i = 0; i < lista.size(); i++) {
                System.out.println("Id: "+lista.get(i).getId());
                System.out.println("Estado: "+lista.get(i).getNombre());
                System.out.println("--------------------------------");
            }
        }
    }
}