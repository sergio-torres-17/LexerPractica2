/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EstructurasDatos;

import Objetos.RegistroLexema;

/**
 *
 * @author SERGIO
 */
public class ControlExpresiones {
    public static final String PATRON_VARIABLES_FLOAT = "float [A-Z]+[A-Ba-z0-9|_]{0,100}.[\\d|\\w]";
    public static final String PATRON_VARIABLES_INT = "int [A-Z]+[A-Ba-z0-9|_]{0,100}.[\\d|\\w]";
    public static final String PATRON_NUMEROS_ENTEROS = "0{0}.\\d";
    public static final String PATRON_NUMEROS_FLOTANTES = "[0-9]+.[0-9]+";
    //public static final String 
    public static final char[] CARACTERES_SIMPLES = {',','=','+','-','*','(',')',';','.','/','-'};
    public static final RegistroLexema[] PALABRAS_RESERVADAS = {
        new RegistroLexema("Programa","Palabra reservada","400"),
        new RegistroLexema("Inicio","Palabra reservada","401"),
        new RegistroLexema("Entero","Palabra reservada","402"),
        new RegistroLexema("Real","Palabra reservada","403"),
        new RegistroLexema("Leer","Palabra reservada","404"),
        new RegistroLexema("Escribir","Palabra reservada","405"),
        new RegistroLexema("Fin","Palabra reservada","406")
    };
    
}
