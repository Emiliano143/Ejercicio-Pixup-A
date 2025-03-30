package org.MendezGalindoEmiliano.pixup.model;

import org.MendezGalindoEmiliano.pixup.util.ReadUtil;
import org.MendezGalindoEmiliano.pixup.vista.Menu;
import org.MendezGalindoEmiliano.pixup.vista.SolicitaDatos;
import java.util.ArrayList;

public class TipoDomicilio implements SolicitaDatos {
    private int id;
    private String descripcion;
    private static ArrayList<TipoDomicilio> lista = new ArrayList<>();

    public TipoDomicilio() {
    }

    public TipoDomicilio(int id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public static void agregarTipoDomicilio(TipoDomicilio tipoDomicilio) {
        lista.add(tipoDomicilio);
    }

    public static ArrayList<TipoDomicilio> getLista() {
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
                    altaTipoDomicilio();
                    break;
                case 2:
                    bajaTipoDomicilio();
                    break;
                case 3:
                    cambioTipoDomicilio();
                    break;
                case 4:
                    verTipoDomicilio();
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

    public void altaTipoDomicilio() {
        Menu.id();
        int nuevoId = ReadUtil.getInstance().leerInt();
        Menu.descripcion();
        String nuevaDescripcion = ReadUtil.getInstance().leer();

        TipoDomicilio nuevoTipoDomicilio = new TipoDomicilio(nuevoId, nuevaDescripcion);
        agregarTipoDomicilio(nuevoTipoDomicilio);
        Menu.alta1();
    }

    public void bajaTipoDomicilio() {
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

    public void cambioTipoDomicilio() {
        if (lista.isEmpty()) {
            Menu.sinDatos();
            return;
        }

        Menu.id();
        int idCambio = ReadUtil.getInstance().leerInt();

        TipoDomicilio tipoDomicilio = null;
        for (TipoDomicilio t : lista) {
            if (t.getId() == idCambio) {
                tipoDomicilio = t;
                break;
            }
        }

        if (tipoDomicilio == null) {
            Menu.noDatos();
            return;
        }

        Menu.cambio6();
        int opcionCambio = ReadUtil.getInstance().leerInt();

        switch (opcionCambio) {
            case 1:
                Menu.cambio();
                int nuevoId = ReadUtil.getInstance().leerInt();
                tipoDomicilio.setId(nuevoId);
                Menu.cambio1();
                break;
            case 2:
                Menu.cambio();
                String nuevaDescripcion = ReadUtil.getInstance().leer();
                tipoDomicilio.setDescripcion(nuevaDescripcion);
                Menu.cambio1();
                break;
            default:
                Menu.opcionInvalida();
                break;
        }
    }

    public void verTipoDomicilio() {
        if (lista.isEmpty()) {
            Menu.sinDatos();
        } else {
            System.out.println("Datos:");
            for (TipoDomicilio tipoDomicilio : lista) {
                System.out.println("Id: " + tipoDomicilio.getId());
                System.out.println("Descripcion: " + tipoDomicilio.getDescripcion());
                System.out.println("--------------------------------");
            }
        }
    }
}
