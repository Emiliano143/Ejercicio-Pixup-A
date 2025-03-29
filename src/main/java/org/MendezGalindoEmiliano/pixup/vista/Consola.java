package org.MendezGalindoEmiliano.pixup.vista;

import org.MendezGalindoEmiliano.pixup.model.Colonia;
import org.MendezGalindoEmiliano.pixup.model.Estado;
import org.MendezGalindoEmiliano.pixup.model.Municipio;
import org.MendezGalindoEmiliano.pixup.util.ReadUtil;

public class Consola implements Ejecutable {
    private static Consola consola;

    public Consola() {
    }

    public static Consola getInstance(){
        if( consola == null )
        {
            consola = new Consola( );
        }
        return consola;
    }

    @Override
    public void run() {
        boolean flag1 = true;
        while (flag1) {
            Menu.principal2();
            int opc1 = ReadUtil.getInstance().leerInt();
            switch (opc1) {
                case 1:
                    boolean flag2 = true;

                    while (flag2) {
                        Menu.principal3();
                        int opc2 = ReadUtil.getInstance().leerInt();
                        switch (opc2) {
                            case 1:
                                Estado estado = new Estado();
                                estado.leerDatos();
                                break;
                            case 2:
                                Municipio municipio = new Municipio();
                                municipio.leerDatos();
                                break;
                            case 3:
                                Colonia colonia = new Colonia();
                                colonia.leerDatos();
                                break;
                            case 4:
                                break;
                            case 5:
                                flag2 = false;
                                break;
                            default:
                                Menu.opcionInvalida();
                                break;
                        }
                    }
                    break;
                case 2:
                    break;
                case 3:
                    flag1 = false;
                    break;
                default:
                    Menu.opcionInvalida();
                    break;
            }
        }
    }
}
