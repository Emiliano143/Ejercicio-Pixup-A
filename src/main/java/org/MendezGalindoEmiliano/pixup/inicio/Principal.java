package org.MendezGalindoEmiliano.pixup.inicio;

import org.MendezGalindoEmiliano.pixup.util.ReadUtil;
import org.MendezGalindoEmiliano.pixup.vista.Consola;
import org.MendezGalindoEmiliano.pixup.vista.Ejecutable;
import org.MendezGalindoEmiliano.pixup.vista.Menu;
import org.MendezGalindoEmiliano.pixup.vista.Ventana;

public class Principal {
    public Principal() {
    }

    public static void main(String[] args){
        boolean flag=true;
        int opcion=0;
        Ejecutable ejecutable=null;

        while (flag){
            ejecutable=null;
            Menu.principal1();
            opcion= ReadUtil.getInstance().leerInt();
            switch( opcion )
            {
                case 1:
                    ejecutable = Consola.getInstance( );
                    break;
                case 2:
                    ejecutable = Ventana.getInstance( );
                    break;
                case 3:
                    flag = false;
                    break;
                default:
                    Menu.opcionInvalida( );
            }
            if( ejecutable != null )
            {
                ejecutable.run( );
            }
        }
    }
}
