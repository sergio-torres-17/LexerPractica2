/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Herramientas;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JTextArea;

/**
 *
 * @author SERGIO
 */
public class ControlArchivos {

    public ArrayList<char[]> devolverCaracteres(String ruta) {
        ArrayList<char[]> dev;
        String linea;
        Scanner sc;
        dev = new ArrayList<>();
        try {
             sc = new Scanner(new File(ruta));
            while (sc.hasNextLine()) {
                linea = sc.nextLine();
                dev.add(linea.toCharArray());
            }
            sc.close();
        } catch (FileNotFoundException ex) {
            System.err.println("Error al abrir el archivo: " + ex.getMessage());
        }
        return dev;
    }

    public ArrayList<char[]> devolverCaracteres(File archivo) {
        ArrayList<char[]> dev;
        Scanner sc;
        String linea;
        dev = new ArrayList<>();
        try {
            sc = new Scanner(archivo);
            while (sc.hasNextLine()) {
                linea = sc.nextLine();
                dev.add(linea.toCharArray());
            }
            sc.close();
        } catch (FileNotFoundException ex) {
            System.err.println("Error al abrir el archivo: " + ex.getMessage());
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
        return dev;
    }

    public static void escribirContenido(ArrayList<char[]> lectura, JTextArea area) {
        String linea, txtTotal = "";
        for (char[] arrayList : lectura) {
            linea = "";
            for (Character character : arrayList)
                linea+= character;
            txtTotal += linea+"\n";
        }
        area.setText(txtTotal);
    }

}
