package simulacion_examen;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {

    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        int opcion = 0;

        do {
            mostrarMenu();
            opcion = input.nextInt();
            switch (opcion) {
                case 1:
                    File fichero = new File(".");
                    for (String f : fichero.list()) {
                        System.out.println(f);
                    }
                    break;
                case 2:
                    crearFichero();
                    break;
                case 3:
                    mostrarFichero();
                    break;
                case 4:
                    sumarNumeros();
                    break;
                case 5:
                    borrarFichero();
                    break;
                case 6:
                    break;
                default:
                    System.out.println("La opción introducida es incorrecta");
                    break;
            }
        } while (opcion != 6);

        input.close();

    }

    public static void mostrarMenu () {

        System.out.println("********** MENU DE FICHEROS **********");
        System.out.println("1. Listar los ficheros de un directorio");
        System.out.println("2. Crear un fichero de números");
        System.out.println("3. Mostrar un fichero");
        System.out.println("4. Sumar un fichero de números ya existente");
        System.out.println("5. Borrar un fichero creado");
        System.out.println("6. Salir del programa");
        System.out.print("Introduce una opción: ");

    }

    public static File crearFichero () {

        int numero = 0;

        input.nextLine();

        System.out.print("Introduce el nombre que deseas darle al fichero: ");
        File fichero = new File(".\\" + input.nextLine() + ".txt");

        try {
            fichero.createNewFile();
            FileWriter writer = new FileWriter(fichero);
            PrintWriter pWriter = new PrintWriter(writer);
            do {
                System.out.print("Introduce números positivos, cuando no desees más introduce -1: ");
                numero = input.nextInt();
                if (fichero.exists() && numero >= 0) {
                    pWriter.println(numero);
                } else if (!fichero.exists()) {
                    System.out.println("El fichero no existe");
                } else if (numero < -1) {
                    System.out.println("El número introducido no es válido");
                }
            } while (numero != -1);
            pWriter.flush();
            writer.close();
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }

        return fichero;

    }

    public static void mostrarFichero () {

        input.nextLine();

        try {
            System.out.print("Introduce el nombre del fichero que deseas mostrar: ");
            File fichero = new File(".\\" + input.nextLine() + ".txt");
            FileReader fr = new FileReader(fichero);
            BufferedReader br = new BufferedReader(fr);
            int i;
            String contenido = "";
            if (fichero.exists()) {
                while ((i = br.read()) != -1) {
                    char caracter = (char)i;
                    contenido += caracter;
                    if (caracter == '\n') {
                        System.out.print(contenido);
                        contenido = "";
                    } 
                } 
            } else {
                System.out.println("El fichero no existe");
            }
            br.close();
            fr.close();
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }

    }

    public static void sumarNumeros () {

        input.nextLine();

        try {
            System.out.print("Introduce el nombre del fichero del que deseas sumar los números introducidos: ");
            File fichero = new File(".\\" + input.nextLine() + ".txt");
            FileReader fr = new FileReader(fichero);
            BufferedReader br = new BufferedReader(fr);
            double suma = 0;
            String contenido = "";
            if (fichero.exists()) {
                while ((contenido = br.readLine()) != null) {
                    suma += Double.parseDouble(contenido); 
                }
                System.out.println("La suma de los números es: " + suma); 
            } else {
                System.out.println("El fichero no existe");
            }
            br.close();
            fr.close();
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }

    }

    public static void borrarFichero () {

        input.nextLine();

        System.out.print("Introduce el nombre del fichero que deseas eliminar: ");
        File fichero = new File(".\\" + input.nextLine() + ".txt");
        if (fichero.exists()) {
            fichero.delete();
            System.out.println("El fichero ha sido eliminado");
        } else {
            System.out.println("El fichero no existe");
        }

    }

}