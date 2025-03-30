package org.MendezGalindoEmiliano.pixup.model;

import org.MendezGalindoEmiliano.pixup.util.ReadUtil;
import org.MendezGalindoEmiliano.pixup.vista.Menu;
import org.MendezGalindoEmiliano.pixup.vista.SolicitaDatos;

import java.util.ArrayList;

public class Colonia implements SolicitaDatos {
    private Integer id;
    private String nombre;
    private String cp;
    private Municipio municipio;
    private static ArrayList<Colonia> lista = new ArrayList<>();

    public Colonia() {
    }

    public Colonia(Integer id, Municipio municipio, String cp, String nombre) {
        this.id = id;
        this.municipio = municipio;
        this.cp = cp;
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public static void agregarColonia(Colonia colonia) {
        lista.add(colonia);
    }

    public static ArrayList<Colonia> getLista() {
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
                    altaColonia();
                    break;
                case 2:
                    bajaColonia();
                    break;
                case 3:
                    cambioColonia();
                    break;
                case 4:
                    verColonia();
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

    public void altaColonia() {
        if (Municipio.getLista().isEmpty()) {
            Menu.noDatos();
            return;
        }
        Menu.colonia();
        Menu.id();
        Integer nuevoId = ReadUtil.getInstance().leerInt();
        Menu.nombre();
        String nuevoNombre = ReadUtil.getInstance().leer();
        Menu.cp();
        String nuevoCp = ReadUtil.getInstance().leer();

        System.out.println("Seleccione el municipio para la colonia:");
        ArrayList<Municipio> municipios = Municipio.getLista();
        for (int i = 0; i < municipios.size(); i++) {
            System.out.println((i + 1) + ". " + municipios.get(i).getNombre() + " ID: "
                    + municipios.get(i).getId());
        }

        int opc = ReadUtil.getInstance().leerInt();
        if (opc < 1 || opc > municipios.size()) {
            Menu.opcionInvalida();
            return;
        }
        Municipio nuevoMunicipio = municipios.get(opc - 1);
        Colonia nuevaColonia = new Colonia(nuevoId, nuevoMunicipio, nuevoCp, nuevoNombre);
        agregarColonia(nuevaColonia);
        Menu.alta1();
    }

    public void bajaColonia() {
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

    public void cambioColonia() {
        if (lista.isEmpty()) {
            Menu.sinDatos();
            return;
        }

        Menu.id();
        int idCambio = ReadUtil.getInstance().leerInt();

        Colonia colonia = null;
        for (Colonia c : lista) {
            if (c.getId().equals(idCambio)) {
                colonia = c;
                break;
            }
        }

        if (colonia == null) {
            Menu.noDatos();
            return;
        }

        Menu.cambio4();
        int opcionCambio = ReadUtil.getInstance().leerInt();

        switch (opcionCambio) {
            case 1:
                Menu.cambio();
                int nuevoId = ReadUtil.getInstance().leerInt();
                colonia.setId(nuevoId);
                Menu.cambio1();
                break;
            case 2:
                Menu.cambio();
                String nuevoNombre = ReadUtil.getInstance().leer();
                colonia.setNombre(nuevoNombre);
                Menu.cambio1();
                break;
            case 3:
                System.out.print("Seleccione el nuevo municipio: ");
                ArrayList<Municipio> municipios = Municipio.getLista();
                for (int i = 0; i < municipios.size(); i++) {
                    System.out.println((i + 1) + ". " + municipios.get(i).getNombre() + " ID: "
                            + municipios.get(i).getId());
                }

                int opc = ReadUtil.getInstance().leerInt();
                if (opc < 1 || opc > municipios.size()) {
                    Menu.opcionInvalida();
                    return;
                }
                Municipio nuevoMunicipio = municipios.get(opc - 1);
                colonia.setMunicipio(nuevoMunicipio);
                Menu.cambio1();
                break;
            case 4:
                Menu.cambio();
                String nuevoCp = ReadUtil.getInstance().leer();
                colonia.setCp(nuevoCp);
                Menu.cambio1();
                break;
            default:
                Menu.opcionInvalida();
                break;
        }
    }

    public void verColonia() {
        if (lista.isEmpty()) {
            Menu.sinDatos();
        } else {
            System.out.println("Datos:");
            for (Colonia colonia : lista) {
                System.out.println("Id: " + colonia.getId());
                System.out.println("Colonia: " + colonia.getNombre());
                System.out.println("Codigo Postal: " + colonia.getCp());
                System.out.println("Municipio al que pertenece: " + colonia.getMunicipio().getNombre()
                        + " ID: " + colonia.getMunicipio().getId());
                System.out.println("--------------------------------");
            }
        }
    }
}
