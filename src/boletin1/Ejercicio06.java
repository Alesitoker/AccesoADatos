package boletin1;

import java.io.*;

public class Ejercicio06 {
    public static void main(String[] args) {
        File dir = new File("C:\\zProyectos\\AccesoADatos\\Esnuevoo\\Copias");
        File originf = new File("C:\\zProyectos\\AccesoADatos\\Esnuevoo\\Pollo.txt");
//        File dir = new File("D:\\Proyectos\\AccesoADatos\\Esnuevoo\\Copias");
//        File originf = new File("D:\\Proyectos\\AccesoADatos\\Esnuevoo\\Pollo.txt");
        File f1 = new File(dir, "part1.txt");
        File f2 = new File(dir, "part2.txt");
        File f3 = new File(dir, "part3.txt");
        File pollo2 = new File(dir, "Pollo.txt");
        FileReader read = null;
        FileWriter wr1;
        FileWriter wr2;
        FileWriter wr3;
        FileReader read2;
        FileReader read3;
        char[] caracteres = new char[15];
        int numRead, numRead2 = 0, numRead3 = 0;

        try {
            f1.createNewFile();
            f2.createNewFile();
            f3.createNewFile();
            read = new FileReader(originf);
            wr1 = new FileWriter(f1);
            wr2 = new FileWriter(f2);
            wr3 = new FileWriter(f3);
            while ((numRead = read.read(caracteres)) != -1) {
                if (numRead == 15) {
                    wr1.write(caracteres, 0, 5);
                    wr2.write(caracteres, 5, 5);
                    wr3.write(caracteres, 10, 5);
                } else {
                    if (numRead <= 5) {
                        wr1.write(caracteres, 0, numRead);
                    } else if (numRead > 5 && numRead <= 10) {
                        wr1.write(caracteres, 0, 5);
                        wr2.write(caracteres, 5, numRead-5);
                    } else if (numRead > 10) {
                        wr1.write(caracteres, 0, 5);
                        wr2.write(caracteres, 5, 5);
                        wr3.write(caracteres, 10, numRead-10);
                    }
                }
            }
            read.close();
            wr1.close();
            wr2.close();
            wr3.close();
            numRead = 0;

            pollo2.createNewFile();
            read = new FileReader(f1);
            read2 = new FileReader(f2);
            read3 = new FileReader(f3);
            wr1 = new FileWriter(pollo2);

            while(numRead != -1 || numRead2 != -1 || numRead3 != -1) {
                numRead = read.read(caracteres, 0, 5);
                numRead2 = read2.read(caracteres, 5, 5);
                numRead3 = read3.read(caracteres, 10, 5);

                if (numRead != -1 && numRead < 5) {
                    wr1.write(caracteres, 0, numRead);
                }
                if (numRead2 != -1 && numRead2 < 5) {
                    wr1.write(caracteres, 5, numRead2);
                }
                if (numRead3 != -1 && numRead3 < 5) {
                    wr1.write(caracteres, 10, numRead3);
                }
                if ((numRead+numRead2+numRead3) == 15) {
                    wr1.write(caracteres);
                }
            }
            read.close();
            read2.close();
            read3.close();
            wr1.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
