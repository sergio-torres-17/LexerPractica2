/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Herramientas;

import EstructurasDatos.ControlExpresiones;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author SERGIO
 */
public class Conversores {
    public static ArrayList<Character> arrayALista(char[] arreglo){
        ArrayList<Character> dev;
        dev = new ArrayList<>();
        for (Character character : dev)
            dev.add(character);
        return dev;
    }
    public static boolean interpretador(String entrada){
        boolean dev = false;
        Pattern patron = Pattern.compile(ControlExpresiones.PATRON_NUMEROS_ENTEROS);
        Matcher mat = patron.matcher(entrada);
        dev = mat.matches();
        return dev;
    }
    public static int caracter_aASCII(char caracter){
        return (int) caracter;
    }
    
}
