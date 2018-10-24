package boletin1;

import utils.Teclado;

import java.io.File;
import java.util.Arrays;

public class Ejercicio03Recursivo {
    public static void main(String[] args) {
        File dir;
        System.out.println("Introduce ruta del directorio");
        dir = new File(Teclado.leerString());
        if (dir.isDirectory()) {
            ver(dir.listFiles());
        }
    }

    private static void ver(File[] dir) {
        String message = String.format("%s - %s", dir[0].getName(), dir[0].isDirectory()?"Directorio":"Fichero");
        System.out.println(message);

            if (dir[0].isDirectory()) {
                ver(dir[0].listFiles());
                if(dir.length > 1) {
                    ver(Arrays.copyOfRange(dir, 1, dir.length));
                }
            } else if (!dir[0].isDirectory() && dir.length != 1){
                ver(Arrays.copyOfRange(dir, 1, dir.length));
            }

    }

}
