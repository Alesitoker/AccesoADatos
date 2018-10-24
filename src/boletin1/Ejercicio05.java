package boletin1;

import java.io.*;

public class Ejercicio05 {
    public static void main(String[] args) {
        File origen = new File("C:\\zProyectos\\AccesoADatos\\src\\boletin1\\nuevo\\NOSOYAGUACATE.txt");
        File destino = new File("C:\\zProyectos\\AccesoADatos\\Esnuevoo\\NOSOYAGUACATE.txt");
//        File origen = new File("D:\\Proyectos\\AccesoADatos\\src\\boletin1\\nuevo\\AHola.txt");
        // Si el destino es un directorio
//        File destino = new File("D:\\Proyectos\\AccesoADatos\\src\\boletin1\\Buenas");
        // el destino es un archivo existente.
//        File destino = new File("D:\\Proyectos\\AccesoADatos\\src\\boletin1\\Buenas\\AHola.txt");
        // el destino es un archivo inexistente.
//        File destino = new File("D:\\Proyectos\\AccesoADatos\\src\\boletin1\\Buenas\\far.txt");
        File f = null;
        BufferedReader bufferReader = null;
        BufferedWriter bufferWriter = null;
        PrintWriter print = null;
        FileWriter wr = null;
        FileReader rd = null;
        boolean bo = false;
        String linea;
        int caracter;
        char caracteres[] = new char[20];

        if (origen.exists() && origen.isFile()) {
            try {
                bufferReader = new BufferedReader(new FileReader(origen));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            if (destino.isDirectory()) {
                try {
                    f = new File(destino, origen.getName());
                    f.createNewFile();
                    print = new PrintWriter(new FileWriter(f), true);
                    while ((linea = bufferReader.readLine()) != null) {
                        print.println(linea);
                    }
                    print.close();
                    bufferReader.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (!destino.isDirectory()) {
                if (destino.exists()) {
                    try {
                        wr = new FileWriter(destino);
                        if (bo) {
                            while ((caracter = bufferReader.read()) >= 0) {
                                wr.write((char) caracter);
                            }
                            bufferReader.close();
                        } else if (!bo) {
                            rd = new FileReader(origen);
                            while ((caracter = rd.read(caracteres)) != -1)  {
                                if (caracter == caracteres.length) {
                                    wr.write(caracteres);
                                } else {
                                    wr.write(caracteres, 0, caracter);
                                }

                            }
                            rd.close();
                        }
                        wr.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (!destino.exists()) {
                    if (bo) {
                        try {
                            throw new IOException("Archivo inexistenten");
                        } catch (IOException e) {
                            System.out.println("Archivo inexistente.");
                        }
                    } else if (!bo) {
                        System.out.println("No se puede realizar la copia.");
                    }
                }
            }
        } else {
            System.out.println("El origen no es un archivo");
        }
    }
}
