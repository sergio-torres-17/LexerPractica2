/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

/**
 *
 * @author SERGIO
 */
public class RegistroError {
    private String lexema;
    private int linea, columna;

    public RegistroError(String lexema, int linea, int columna) {
        this.lexema = lexema;
        this.linea = linea;
        this.columna = columna;
    }

    public String getLexema() {
        return lexema;
    }

    public int getLinea() {
        return linea;
    }

    public int getColumna() {
        return columna;
    }

    @Override
    public String toString() {
        return "RegistroError{" + "lexema=" + lexema + ", linea=" + linea + ", columna=" + columna + '}';
    }

    
}
