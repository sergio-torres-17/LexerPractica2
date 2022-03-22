/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import java.util.ArrayList;

/**
 *
 * @author SERGIO
 */
public class Testing {

    public static int codigoTipoCaracter;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Ev "+evaluadorCaracter('#'));
    }

    public static ArrayList<String> analizar(char[] entrada) throws InterruptedException {
        ArrayList<String> lexemas;
        String lexemaActual = "";
        lexemas = new ArrayList<>();
        if (entrada.length > 0) {
            for (int i = 0; i < entrada.length; i++) {
                System.out.println("Token: " + entrada[i] + " tipo: " + evaluadorCaracter(entrada[i]));
                if (-2 != evaluadorCaracter(entrada[i])) {//Validamos el cambio del tipo de caracter
                    lexemas.add(lexemaActual);//Si hay cambio agregamos el código actrual
                    if (evaluadorCaracter(entrada[i]) == 2)//Sio es un caracter lo agregamos
                    {
                        lexemas.add(String.valueOf(entrada[i]));
                    }
                    lexemaActual = "";
                } else {
                    System.out.println("Token: " + entrada[i]);
                    if (evaluadorCaracter(entrada[i]) != 1) {
                        lexemaActual += entrada[i];
                    }
                }
                if (evaluadorCaracter(entrada[i]) == 1) {
                    lexemaActual += entrada[i];
                    codigoTipoCaracter = evaluadorCaracter(entrada[i]);
                }
                Thread.sleep(250);
            }
        }
        return lexemas;
    }

    public static int evaluadorCaracter(char token) {
        if (token != ' ') {
            if (Character.isLetter(token)) {
                return 0;
            }
            if (Character.isDigit(token)) {
                return 1;
            }
            if (token == ';' || token == '=' || token == '+' || token == '-'
                    || token == '*' || token == '(' || token == ')'
                    || token == '{' || token == '}' || token == ',') {
                return 2;
            }
            if (Character.isWhitespace(token)) {
                return -2;
            }
        }
        return -1;
    }
}
