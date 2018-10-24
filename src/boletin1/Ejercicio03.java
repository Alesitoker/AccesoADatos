package boletin1;

import utils.Teclado;

import java.io.File;

public class Ejercicio03 {
    public static void main(String[] args) {
        File dir;
        System.out.println("Introduce ruta del directorio:");
        dir = new File(Teclado.leerString());
        ver(dir);
        Teclado.cerrarTeclado();
    }
    public static void ver(File dir) {
        int i;
        File direc[] = dir.listFiles();

        if (dir.isDirectory()) {
            for (i = 0; i < direc.length; i++) {
                System.out.printf("%s - %s\n", direc[i].getName(), direc[i].isDirectory()?"Directorio":"Fichero");
                if (direc[i].isDirectory()) {
                    ver(direc[i]);
                }
            }
        } else {
            System.out.println("La ruta es erronea o no es un directorio");
        }
    }
}
