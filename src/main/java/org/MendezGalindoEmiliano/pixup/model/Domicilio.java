package org.MendezGalindoEmiliano.pixup.model;

import org.MendezGalindoEmiliano.pixup.util.ReadUtil;
import org.MendezGalindoEmiliano.pixup.vista.Menu;
import org.MendezGalindoEmiliano.pixup.vista.SolicitaDatos;

import java.util.ArrayList;

public class Domicilio implements SolicitaDatos {
    private Integer id;
    private String calle;
    private String numeroExt;
    private String numeroInt;
    private Usuario usuario;
    private Colonia colonia;
    private TipoDomicilio tipoDomicilio;
    private static ArrayList<Domicilio> lista = new ArrayList<>();

    public Domicilio() {
    }

    public Domicilio(Integer id, Usuario usuario, Colonia colonia, TipoDomicilio tipoDomicilio, String numeroExt, String numeroInt, String calle) {
        this.id = id;
        this.usuario = usuario;
        this.colonia = colonia;
        this.tipoDomicilio = tipoDomicilio;
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

    public TipoDomicilio getTipoDomicilio() {
        return tipoDomicilio;
    }

    public void setTipoDomicilio(TipoDomicilio tipoDomicilio) {
        this.tipoDomicilio = tipoDomicilio;
    }

    public String getNumeroExt() {
        return numeroExt;
    }

    public void setNumeroExt(String numeroExt) {
        this.numeroExt = numeroExt;
    }

    public String getNumeroInt() {
        return numeroInt;
    }

    public void setNumeroInt(String numeroInt) {
        this.numeroInt = numeroInt;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public static void agregarDomicilio(Domicilio domicilio) {
        lista.add(domicilio);
    }

    public static ArrayList<Domicilio> getLista() {
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
                    altaDomicilio();
                    break;
                case 2:
                    bajaDomicilio();
                    break;
                case 3:
                    cambioDomicilio();
                    break;
                case 4:
                    verDomicilio();
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

    public void altaDomicilio() {
        if (Usuario.getLista().isEmpty() || Colonia.getLista().isEmpty() || TipoDomicilio.getLista().isEmpty()) {
            Menu.noDatos();
            return;
        }
        Menu.domicilio();
        Menu.id();
        Integer nuevoId = ReadUtil.getInstance().leerInt();
        Menu.calle();
        String nuevaCalle = ReadUtil.getInstance().leer();
        Menu.numExt();
        String nuevoNumeroExt = ReadUtil.getInstance().leer();
        Menu.numInt();
        String nuevoNumeroInt = ReadUtil.getInstance().leer();

        System.out.println("Seleccione el usuario para el domicilio:");
        ArrayList<Usuario> usuarios = Usuario.getLista();
        for (int i = 0; i < usuarios.size(); i++) {
            System.out.println((i + 1) + ". " + usuarios.get(i).getNombre() + "  ID: " + usuarios.get(i).getId());
        }
        int usuarioOpc = ReadUtil.getInstance().leerInt();
        if (usuarioOpc < 1 || usuarioOpc > usuarios.size()) {
            Menu.opcionInvalida();
            return;
        }
        Usuario usuarioSeleccionado = usuarios.get(usuarioOpc - 1);

        System.out.println("Seleccione la colonia para el domicilio:");
        ArrayList<Colonia> colonias = Colonia.getLista();
        for (int i = 0; i < colonias.size(); i++) {
            System.out.println((i + 1) + ". " + colonias.get(i).getNombre() + "  ID: " + colonias.get(i).getId());
        }
        int coloniaOpc = ReadUtil.getInstance().leerInt();
        if (coloniaOpc < 1 || coloniaOpc > colonias.size()) {
            Menu.opcionInvalida();
            return;
        }
        Colonia coloniaSeleccionada = colonias.get(coloniaOpc - 1);

        System.out.println("Seleccione el tipo de domicilio:");
        ArrayList<TipoDomicilio> tipos = TipoDomicilio.getLista();
        for (int i = 0; i < tipos.size(); i++) {
            System.out.println((i + 1) + "  ID: " + tipos.get(i).getId());
        }
        int tipoOpc = ReadUtil.getInstance().leerInt();
        if (tipoOpc < 1 || tipoOpc > tipos.size()) {
            Menu.opcionInvalida();
            return;
        }
        TipoDomicilio tipoDomicilioSeleccionado = tipos.get(tipoOpc - 1);

        Domicilio nuevoDomicilio = new Domicilio(nuevoId, usuarioSeleccionado, coloniaSeleccionada, tipoDomicilioSeleccionado, nuevoNumeroExt, nuevoNumeroInt, nuevaCalle);
        agregarDomicilio(nuevoDomicilio);
        Menu.alta1();
    }

    public void bajaDomicilio() {
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

    public void cambioDomicilio() {
        if (lista.isEmpty()) {
            Menu.sinDatos();
            return;
        }

        Menu.id();
        int idCambio = ReadUtil.getInstance().leerInt();

        Domicilio domicilio = null;
        for (Domicilio d : lista) {
            if (d.getId().equals(idCambio)) {
                domicilio = d;
                break;
            }
        }

        if (domicilio == null) {
            Menu.noDatos();
            return;
        }

        Menu.cambio5();
        int opcionCambio = ReadUtil.getInstance().leerInt();

        switch (opcionCambio) {
            case 1:
                Menu.cambio();
                int nuevoId = ReadUtil.getInstance().leerInt();
                domicilio.setId(nuevoId);
                Menu.cambio1();
                break;
            case 2:
                Menu.cambio();
                String nuevaCalle = ReadUtil.getInstance().leer();
                domicilio.setCalle(nuevaCalle);
                Menu.cambio1();
                break;
            case 3:
                Menu.cambio();
                String nuevoNumeroExt = ReadUtil.getInstance().leer();
                domicilio.setNumeroExt(nuevoNumeroExt);
                Menu.cambio1();
                break;
            case 4:
                Menu.cambio();
                String nuevoNumeroInt = ReadUtil.getInstance().leer();
                domicilio.setNumeroInt(nuevoNumeroInt);
                Menu.cambio1();
                break;
            default:
                Menu.opcionInvalida();
                break;
        }
    }

    public void verDomicilio() {
        if (lista.isEmpty()) {
            Menu.sinDatos();
        } else {
            System.out.println("Datos:");
            for (Domicilio domicilio : lista) {
                System.out.println("Id: " + domicilio.getId());
                System.out.println("Calle: " + domicilio.getCalle());
                System.out.println("Numero Exterior: " + domicilio.getNumeroExt());
                System.out.println("Numero Interior: " + domicilio.getNumeroInt());
                System.out.println("Usuario: " + domicilio.getUsuario().getNombre() + " ID: " + domicilio.getUsuario().getId());
                System.out.println("Colonia: " + domicilio.getColonia().getNombre() + " ID: " + domicilio.getColonia().getId());
                System.out.println("Tipo Domicilio: " + "  ID: " + domicilio.getTipoDomicilio().getId());
                System.out.println("--------------------------------");
            }
        }
    }
}

