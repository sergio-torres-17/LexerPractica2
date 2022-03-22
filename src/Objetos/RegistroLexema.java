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
public class RegistroLexema implements Cloneable{
    private String lexema;
    private String clasificación;
    private String Descripcion;
    private String atributo;
      private String repeticiones;
    private String linea; 

    public RegistroLexema(String lexema, String clasificación, String Descripcion, String atributo, String repeticiones, String linea) {
        this.lexema = lexema;
        this.clasificación = clasificación;
        this.Descripcion = Descripcion;
        this.atributo = atributo;
        this.repeticiones = repeticiones;
        this.linea = linea;
    }

    public void setClasificación(String clasificación) {
        this.clasificación = clasificación;
    }

    public void setRepeticiones(String repeticiones) {
        this.repeticiones = repeticiones;
    }

    public void setLinea(String linea) {
        this.linea = linea;
    }

    public String getRepeticiones() {
        return repeticiones;
    }

    public String getLinea() {
        return linea;
    }

    public RegistroLexema(String lexema, String clasificación, String atributo) {
        this.lexema = lexema;
        this.clasificación = clasificación;
        this.atributo = atributo;
    }
    public RegistroLexema(String lexema, String clasificación,String Descripcion, String atributo) {
        this.lexema = lexema;
        this.clasificación = clasificación;
        this.Descripcion = Descripcion;
        this.atributo = atributo;
    }

    public RegistroLexema(String lexema) {
        this.lexema = lexema;
    }

    public RegistroLexema(String lexema, String clasificación) {
        this.lexema = lexema;
        this.clasificación = clasificación;
    }
    
    
    public String getLexema() {
        return lexema;
    }
    public String getClasificación() {
        return clasificación;
    }
    public String getAtributo() {
        return atributo;
    }
    public void setLexema(String lexema) {
        this.lexema = lexema;
    }
    public void setAtributo(String atributo) {
        this.atributo = atributo;
    }
    public String getDescripcion() {
        return Descripcion;
    }
    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }
    @Override
    public String toString() {
        return "RegistroLexema{" + "lexema=" + lexema + ", clasificaci\u00f3n=" + clasificación + ", atributo=" + atributo + '}';
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return (RegistroLexema)super.clone(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
