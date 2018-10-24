package boletin1;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class Ejercicio02 {
    public static void main(String[] args) {
        File dir = new File("src\\boletin1\\nuevo");
        File f1 = new File(dir, "aguacate.txt");
        File f2 = new File(dir, "yogur.txt");
        File dir2 = new File(dir.getAbsolutePath() + "\\in");
        File f3 = new File(dir2, "Val.txt");
        File f4 = new File(dir, "AHola.txt");

        if(dir.mkdir()) {
            try {
                System.out.printf("%s\n", f1.createNewFile()?"El fichero aguacate.txt se ha creado correctamente.":"No se a podido crear el fichero aguacate.txt.");
                System.out.printf("%s\n", f2.createNewFile()?"El fichero yogur.txt se ha creado correctamente.":"No se a podido crear el fichero yogur.txt.");
                System.out.printf("%s\n", f4.createNewFile()?"El fichero Hola.txt se ha creado correctamente.":"No se a podido crear el fichero Hola.txt.");
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(dir2.mkdir()) {
                try {
                    System.out.printf("%s\n", f3.createNewFile()?"El fichero Val.txt se ha creado correctamente.":"No se a podido crear el fichero Val.txt.");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("No se ha podido crear el directorio in.");
            }
        } else {
            System.out.println("No se ha podido crear el directorio nuevo.");
        }
        System.out.printf("%s\n", f1.renameTo(new File(dir, "NOSOYAGUACATE.txt"))?"El fichero aguacate.txt se ha renombrado a NOSOYAGUACATE.txt.":"No se a podido renombrar el fichero aguacate.txt.");
        System.out.printf("%s\n", f2.delete()?"El fichero yogut.txt ha sido eliminado correctamente.":"No se ha podido eleminar el fichero.");

        System.out.printf("Ruta de nuevo: %s\nContenido de nuevo: %s\nRuta de in: %s\nContenido de in: %s\n", dir.getAbsolutePath(), Arrays.toString(dir.list()), dir2.getAbsolutePath(), Arrays.toString(dir2.list()));
    }
}
