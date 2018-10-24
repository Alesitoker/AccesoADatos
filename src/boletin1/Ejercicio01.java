package boletin1;

import java.io.File;
import java.util.Scanner;

public class Ejercicio01 {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        File file;
        String yes = "si", no = "no", ruta = "";

        System.out.println("Introduce ruta del fichero:");
        ruta = teclado.next();
        file = new File(ruta);
        if (file.exists() && !file.isDirectory())
            System.out.printf("Nombre: %s\n¿Es ejecutable? %s\n¿Es visible? %s\nRuta relativa: %s\nRuta absoluta: %s\nTamaño: %d bytes\n", file.getName(), file.canExecute() ? yes : no, file.isHidden() ? no : yes, file.getPath(), file.getAbsolutePath(), file.length());
        else
            System.out.println("El fichero no existe");
    }
}
