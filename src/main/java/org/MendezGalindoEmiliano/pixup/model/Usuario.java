package org.MendezGalindoEmiliano.pixup.model;

import org.MendezGalindoEmiliano.pixup.util.ReadUtil;
import org.MendezGalindoEmiliano.pixup.vista.Menu;
import org.MendezGalindoEmiliano.pixup.vista.SolicitaDatos;

import java.util.ArrayList;

public class Usuario implements SolicitaDatos {
    private Integer id;
    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    private String contrasena;
    private String email;
    private String rfc;
    private static ArrayList<Usuario> lista = new ArrayList<>();

    public Usuario() {
    }

    public Usuario(Integer id, String nombre, String primerApellido, String segundoApellido, String contrasena, String email, String rfc) {
        this.id = id;
        this.nombre = nombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.contrasena = contrasena;
        this.email = email;
        this.rfc = rfc;
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

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public static void agregarUsuario(Usuario usuario) {
        lista.add(usuario);
    }

    public static ArrayList<Usuario> getLista() {
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
                    altaUsuario();
                    break;
                case 2:
                    bajaUsuario();
                    break;
                case 3:
                    cambioUsuario();
                    break;
                case 4:
                    verUsuario();
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

    public void altaUsuario() {
        Menu.id();
        int nuevoId = ReadUtil.getInstance().leerInt();
        Menu.nombre();
        String nuevoNombre = ReadUtil.getInstance().leer();
        Menu.apellido1();
        String nuevoApellido1 = ReadUtil.getInstance().leer();
        Menu.apellido2();
        String nuevoApellido2 = ReadUtil.getInstance().leer();
        Menu.contra();
        String nuevaContrasena = ReadUtil.getInstance().leer();
        Menu.email();
        String nuevoEmail = ReadUtil.getInstance().leer();
        Menu.rfc();
        String nuevoRfc = ReadUtil.getInstance().leer();

        Usuario nuevoUsuario = new Usuario(nuevoId, nuevoNombre, nuevoApellido1, nuevoApellido2, nuevaContrasena, nuevoEmail, nuevoRfc);
        agregarUsuario(nuevoUsuario);
        Menu.alta1();
    }

    public void bajaUsuario() {
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

    public void cambioUsuario() {
        if (lista.isEmpty()) {
            Menu.sinDatos();
            return;
        }

        Menu.id();
        int idCambio = ReadUtil.getInstance().leerInt();

        Usuario usuario = null;
        for (Usuario u : lista) {
            if (u.getId().equals(idCambio)) {
                usuario = u;
                break;
            }
        }

        if (usuario == null) {
            Menu.noDatos();
            return;
        }

        Menu.cambio7();
        int opcionCambio = ReadUtil.getInstance().leerInt();

        switch (opcionCambio) {
            case 1:
                Menu.cambio();
                int nuevoId = ReadUtil.getInstance().leerInt();
                usuario.setId(nuevoId);
                Menu.cambio1();
                break;
            case 2:
                Menu.cambio();
                String nuevoNombre = ReadUtil.getInstance().leer();
                usuario.setNombre(nuevoNombre);
                Menu.cambio1();
                break;
            case 3:
                Menu.cambio();
                String nuevoApellido1 = ReadUtil.getInstance().leer();
                usuario.setPrimerApellido(nuevoApellido1);
                Menu.cambio1();
                break;
            case 4:
                Menu.cambio();
                String nuevoApellido2 = ReadUtil.getInstance().leer();
                usuario.setSegundoApellido(nuevoApellido2);
                Menu.cambio1();
                break;
            case 5:
                Menu.cambio();
                String nuevaContrasena = ReadUtil.getInstance().leer();
                usuario.setContrasena(nuevaContrasena);
                Menu.cambio1();
                break;
            case 6:
                Menu.cambio();
                String nuevoEmail = ReadUtil.getInstance().leer();
                usuario.setEmail(nuevoEmail);
                Menu.cambio1();
                break;
            case 7:
                Menu.cambio();
                String nuevoRfc = ReadUtil.getInstance().leer();
                usuario.setRfc(nuevoRfc);
                Menu.cambio1();
                break;
            default:
                Menu.opcionInvalida();
                break;
        }
    }

    public void verUsuario() {
        if (lista.isEmpty()) {
            Menu.sinDatos();
        } else {
            System.out.println("Datos:");
            for (Usuario usuario : lista) {
                System.out.println("Id: " + usuario.getId());
                System.out.println("Nombre: " + usuario.getNombre());
                System.out.println("Primer Apellido: " + usuario.getPrimerApellido());
                System.out.println("Segundo Apellido: " + usuario.getSegundoApellido());
                System.out.println("Contrasena: " + usuario.getContrasena());
                System.out.println("Email: " + usuario.getEmail());
                System.out.println("RFC: " + usuario.getRfc());
                System.out.println("--------------------------------");
            }
        }
    }
}
