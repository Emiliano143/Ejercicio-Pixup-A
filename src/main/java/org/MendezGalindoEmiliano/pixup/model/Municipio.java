package org.MendezGalindoEmiliano.pixup.model;

import org.MendezGalindoEmiliano.pixup.util.ReadUtil;
import org.MendezGalindoEmiliano.pixup.vista.Menu;
import org.MendezGalindoEmiliano.pixup.vista.SolicitaDatos;
import java.util.ArrayList;

public class Municipio implements SolicitaDatos {
    private Integer id;
    private String nombre;
    private Estado estado;
    public static ArrayList<Municipio> lista = new ArrayList<>();

    public Municipio() {
    }

    public Municipio(Integer id, Estado estado, String nombre) {
        this.id = id;
        this.estado = estado;
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public static void agregarMunicipio(Municipio municipio) {
        lista.add(municipio);
    }

    public static ArrayList<Municipio> getLista() {
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
                    altaMunicipio();
                    break;
                case 2:
                    bajaMunicipio();
                    break;
                case 3:
                    cambioMunicipio();
                    break;
                case 4:
                    verMunicipio();
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

    public void altaMunicipio() {
        if (Estado.getLista().isEmpty()) {
            Menu.noDatos();
            return;
        }
        Menu.municipio();
        Menu.id();
        Integer nuevoId = ReadUtil.getInstance().leerInt();
        Menu.nombre();
        String nuevoNombre = ReadUtil.getInstance().leer();

        System.out.println("Seleccione el estado para el municipio:");
        ArrayList<Estado> estados = Estado.getLista();
        for (int i = 0; i < estados.size(); i++) {
            System.out.println((i + 1) + estados.get(i).getNombre() + " ID: " + estados.get(i).getId());
        }

        int opc = ReadUtil.getInstance().leerInt();
        if (opc < 1 || opc > estados.size()) {
            Menu.opcionInvalida();
            return;
        }
        Estado nuevoEstado = estados.get(opc - 1);
        Municipio nuevoMun = new Municipio(nuevoId, nuevoEstado, nuevoNombre);
        agregarMunicipio(nuevoMun);
        Menu.alta1();
    }

    public void bajaMunicipio() {
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

    public void cambioMunicipio() {
        if (lista.isEmpty()) {
            Menu.sinDatos();
            return;
        }

        Menu.id();
        int idCambio = ReadUtil.getInstance().leerInt();

        Municipio municipio = null;
        for (Municipio e : lista) {
            if (e.getId().equals(idCambio)) {
                municipio = e;
                break;
            }
        }

        if (municipio == null) {
            Menu.noDatos();
            return;
        }

        Menu.cambio3();
        int opcionCambio = ReadUtil.getInstance().leerInt();

        switch (opcionCambio) {
            case 1:
                Menu.cambio();
                int nuevoId = ReadUtil.getInstance().leerInt();
                municipio.setId(nuevoId);
                Menu.cambio1();
                break;
            case 2:
                Menu.cambio();
                String nuevoNombre = ReadUtil.getInstance().leer();
                municipio.setNombre(nuevoNombre);
                Menu.cambio1();
                break;
            case 3:
                System.out.print("Ingrese el numero del estado: ");
                ArrayList<Estado> estados = Estado.getLista();
                for (int i = 0; i < estados.size(); i++) {
                    System.out.println((i + 1) + ". " + estados.get(i).getNombre()
                            + " ID: " + estados.get(i).getId());
                }

                int opc = ReadUtil.getInstance().leerInt();
                if (opc < 1 || opc > estados.size()) {
                    Menu.opcionInvalida();
                    return;
                }
                Estado nuevoEstado = estados.get(opc - 1);
                municipio.setEstado(nuevoEstado);
                Menu.cambio1();
                break;
            default:
                Menu.opcionInvalida();
                break;
        }
    }

    public void verMunicipio() {
        if (lista.isEmpty()) {
            Menu.sinDatos();
        } else {
            System.out.println("Datos:");
            for (Municipio municipio : lista) {
                System.out.println("Id: " + municipio.getId());
                System.out.println("Municipio: " + municipio.getNombre());
                System.out.println("Estado al que pertenece: " + municipio.getEstado().getNombre()
                        + " ID: " + municipio.getEstado().getId());
                System.out.println("--------------------------------");
            }
        }
    }
}