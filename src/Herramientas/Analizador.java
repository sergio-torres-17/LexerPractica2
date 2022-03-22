package Herramientas;

import EstructurasDatos.ControlExpresiones;
import Objetos.RegistroError;
import Objetos.RegistroLexema;
import java.util.ArrayList;

/**
 *
 * @author SERGIO
 */
public class Analizador {

    private int posiciónColumna, posiciónFila, tipoToken;
    private ArrayList<RegistroLexema> regLexemas;
    private ArrayList<RegistroError> regErrores;
    private String lexemaActual;
    private String defTipo;
    private boolean estadoError;

    public Analizador() {
        this.tipoToken = 0;
        this.posiciónColumna = this.posiciónFila = 1;
        this.estadoError = false;
        this.regLexemas = new ArrayList<>();
        this.regErrores = new ArrayList<>();
    }

    public boolean análisis(ArrayList<char[]> lineas) throws CloneNotSupportedException {
        ArrayList<String> tokensEnLinea;
        tokensEnLinea = new ArrayList<>();
        RegistroError regError;
        for (char[] linea : lineas) {
            tokensEnLinea = extraerTokens(linea);
            for (String string : tokensEnLinea) {
                this.lexemaActual = string;
                if(MultiHerramientas.EvaluarSiEsDeclarador(this.lexemaActual))
                    defTipo = lexemaActual;
                if (string.length() > 0) {
                    if (!perteneceLenguaje(this.lexemaActual)) {
                        regError = new RegistroError(this.lexemaActual, this.posiciónFila, this.posiciónColumna);
                        if (!MultiHerramientas.existeErrorEnLista(regErrores, regError)
                                && !MultiHerramientas.existeLexemaEnLista(regLexemas, new RegistroLexema(this.lexemaActual))) {
                            this.estadoError = true;
                            this.regErrores.add(regError);
                        }
                    }
                }
                this.posiciónColumna++;
            }
            this.posiciónFila++;
        }
        return true;
    }
    public ArrayList<String> extraerTokens(char[] linea) {
        ArrayList<String> dev = new ArrayList<>();
        String lexema = "",lexAux = "";
        this.posiciónColumna = 0;
        if (linea.length > 0) {
            for (int i = 0; i < linea.length; i++) {

                if (i > 0) {
                    if (evaluadorCaracter(linea[i]) == 1 || (evaluadorCaracter(linea[i - 1]) == 1 && linea[i] == '.')) {
                        lexema += linea[i];
                        this.tipoToken = evaluadorCaracter(linea[i]);
                    }
                }
                if (this.tipoToken != this.evaluadorCaracter(linea[i])) {
                    dev.add(lexema);
                    if (evaluadorCaracter(linea[i]) == 2)//Si no es un caracter lo agregamos
                    {
                        dev.add(String.valueOf(linea[i]));
                          
                    } else if (evaluadorCaracter(linea[i]) == 0) {
                        lexAux += linea[i];
                    }else if(linea[i] == ' '){
                        dev.add(lexAux);
                        lexAux = "";
                    }
                    lexema = "";
                } else {
                    if (evaluadorCaracter(linea[i]) != 1 && linea[i] != '.') {
                        lexema += linea[i];
                    }
                }
                if (evaluadorCaracter(linea[i]) == -1 & (linea[i]) != ' ' && (linea[i]) != '.') {
                    dev.add(lexema);
                    dev.add(String.valueOf(linea[i]));
                }
                System.out.println("Lexema: " + lexema);
                
            }
            
        }
        return dev;
    }

    private boolean perteneceLenguaje(String lexema) throws CloneNotSupportedException {
        if (lexema.length() > 0) {
            if (lexema.length() == 1) {
                if (esCaracterSimple(ControlExpresiones.CARACTERES_SIMPLES, lexema)) {
                    System.out.println("Palabra que es caracter simple "+lexema);
                    return true;
                }
            } else {
                if (esReservada(ControlExpresiones.PALABRAS_RESERVADAS, lexema)) {
                    System.out.println("Palabra que pertence como reservada "+lexema);
                    return true;
                } else if (esIdentificador(lexema)) {
                    System.out.println("Palabra que es identificador como reservada "+lexema);
                    return true;
                } else if (esNumEntero(lexema)) {
                    System.out.println("Palabra que es ENTERO "+lexema);
                    return true;
                } else if (esNumPuntoFlotante(lexema)) {
                    System.out.println("Palabra que es numero de punto flotante "+lexema);
                    return true;
                }
            }
        }
        return false;
    }
    private boolean esReservada(RegistroLexema[] palabrasReservadas, String lexema) {
        for (RegistroLexema palabraReservada : palabrasReservadas) {
            if (palabraReservada.getLexema().toLowerCase().equals(lexema.toLowerCase())) {
                palabraReservada = MultiHerramientas.buscarDescripcion(palabraReservada);
               if(!MultiHerramientas.existeLexemaEnLista(regLexemas, palabraReservada)){
                   palabraReservada.setRepeticiones("1");
                   palabraReservada.setLinea(String.valueOf(this.posiciónFila)+",");
                    this.regLexemas.add(palabraReservada);
                    
               }
               else {
                   RegistroLexema aux = MultiHerramientas.existeLexemaEnLista(regLexemas, palabraReservada, estadoError);
                   int repeticiones, posicionLexema;
                   repeticiones = Integer.parseInt(aux.getRepeticiones())+1;
                   palabraReservada.setRepeticiones(String.valueOf(repeticiones));
                   palabraReservada.setLinea(aux.getLinea()+String.valueOf(this.posiciónFila)+",");
                   posicionLexema = MultiHerramientas.ExtraerPosicion(regLexemas, palabraReservada);
                   this.regLexemas.remove(posicionLexema);
                   this.regLexemas.add(posicionLexema, palabraReservada);
               }
                return true;
            }
        }
        return false;
    }
     private boolean esCaracterSimple(char[] caracteresSimples, String palabra) {
        RegistroLexema lexema;
        if (palabra.length() == 1) {
            for (char caracterSimple : caracteresSimples) {
                if (palabra.toCharArray()[0] == caracterSimple) {
                    lexema = new RegistroLexema(palabra, "Carácter Simple", String.valueOf(Conversores.caracter_aASCII(palabra.toCharArray()[0])));
                    lexema = MultiHerramientas.buscarDescripcion(lexema);
                    
                    if(!MultiHerramientas.existeLexemaEnLista(regLexemas, lexema)){
                   lexema.setRepeticiones("1");
                   lexema.setLinea(String.valueOf(this.posiciónFila)+",");
                    this.regLexemas.add(lexema);
               }
               else {
                   RegistroLexema aux = MultiHerramientas.existeLexemaEnLista(regLexemas, lexema, estadoError);
                   int repeticiones, posicionLexema;
                   repeticiones = Integer.parseInt(aux.getRepeticiones())+1;
                   lexema.setRepeticiones(String.valueOf(repeticiones));
                   lexema.setLinea(aux.getLinea()+String.valueOf(this.posiciónFila)+",");
                   posicionLexema = MultiHerramientas.ExtraerPosicion(regLexemas, lexema);
                   this.regLexemas.remove(posicionLexema);
                   this.regLexemas.add(posicionLexema, lexema);
               }
                    return true;
                }
            }
        }
        return false;
    }
    private boolean esIdentificador(String lexema) throws CloneNotSupportedException {
        char[] lexemaCaracter;
        String nNuevoAtributo;
        RegistroLexema lexemaO;
        lexemaCaracter = lexema.toCharArray();
        nNuevoAtributo = MultiHerramientas.maxAttrLexema(regLexemas);
        if ( Character.isLetterOrDigit(lexemaCaracter[lexemaCaracter.length - 1])&&!Character.isDigit(lexemaCaracter[0])) {
            lexemaO = new RegistroLexema(lexema, "Identificador", nNuevoAtributo);
            RegistroLexema aux = MultiHerramientas.existeLexemaEnLista(regLexemas, lexemaO, true);
            if( aux == null){
                lexemaO.setDescripcion(defTipo);
                if(!MultiHerramientas.existeLexemaEnLista(regLexemas, lexemaO)){
                   lexemaO.setRepeticiones("1");
                   lexemaO.setLinea(String.valueOf(this.posiciónFila)+",");
                    this.regLexemas.add(lexemaO);
                    
               }
               else {
                   RegistroLexema aux1 = MultiHerramientas.existeLexemaEnLista(regLexemas, lexemaO, estadoError);
                   int repeticiones, posicionLexema;
                   repeticiones = Integer.parseInt(aux1.getRepeticiones())+1;
                   lexemaO.setRepeticiones(String.valueOf(repeticiones));
                   lexemaO.setLinea(aux1.getLinea()+String.valueOf(this.posiciónFila)+",");
                   posicionLexema = MultiHerramientas.ExtraerPosicion(regLexemas, lexemaO);
                   this.regLexemas.remove(posicionLexema);
                   this.regLexemas.add(posicionLexema, lexemaO);
               }
            }else{
               lexemaO = (RegistroLexema)aux.clone();
               if(!MultiHerramientas.existeLexemaEnLista(regLexemas, lexemaO)){
                   lexemaO.setRepeticiones("1");
                   lexemaO.setLinea(String.valueOf(this.posiciónFila)+",");
                    this.regLexemas.add(lexemaO);
                  
               }
               else {
                   RegistroLexema aux1 = MultiHerramientas.existeLexemaEnLista(regLexemas, lexemaO, estadoError);
                   int repeticiones, posicionLexema;
                   repeticiones = Integer.parseInt(aux1.getRepeticiones())+1;
                   lexemaO.setRepeticiones(String.valueOf(repeticiones));
                   lexemaO.setLinea(aux1.getLinea()+String.valueOf(this.posiciónFila)+",");
                   posicionLexema = MultiHerramientas.ExtraerPosicion(regLexemas, lexemaO);
                   this.regLexemas.remove(posicionLexema);
                   this.regLexemas.add(posicionLexema, lexemaO);
               }
            }
            
        } else {
            return false;
        }
        return true;
    }
    private boolean esNumEntero(String lexema) {
        char[] lexChar = lexema.toCharArray();
        String atributo = MultiHerramientas.buscarAtributoPorClasificación(regLexemas, "Número entero");
        RegistroLexema nuevoLexema;
        if (MultiHerramientas.esNuemeroEntero(lexema)) {
            if ((lexChar[0] != '0' && !lexema.contains("."))) {
                nuevoLexema = new RegistroLexema(lexema, "Enteros", atributo);
                nuevoLexema = MultiHerramientas.buscarDescripcion(nuevoLexema);
                if(!MultiHerramientas.existeLexemaEnLista(regLexemas, nuevoLexema)){
                   nuevoLexema.setRepeticiones("1");
                   nuevoLexema.setLinea(String.valueOf(this.posiciónFila)+",");
                    this.regLexemas.add(nuevoLexema);
                    
               }
               else {
                   RegistroLexema aux = MultiHerramientas.existeLexemaEnLista(regLexemas, nuevoLexema, estadoError);
                   int repeticiones, posicionLexema;
                   repeticiones = Integer.parseInt(aux.getRepeticiones())+1;
                   nuevoLexema.setRepeticiones(String.valueOf(repeticiones));
                   nuevoLexema.setLinea(aux.getLinea()+String.valueOf(this.posiciónFila)+",");
                   posicionLexema = MultiHerramientas.ExtraerPosicion(regLexemas, nuevoLexema);
                   this.regLexemas.remove(posicionLexema);
                   this.regLexemas.add(posicionLexema, nuevoLexema);
               }
            }
        }
        return false;
    }

    private boolean esNumPuntoFlotante(String lexema) {
        char[] lexChar = lexema.replace(' ', '\0').toCharArray();
        String atributo = MultiHerramientas.buscarAtributoPorClasificación(regLexemas, "Número de punto flotante");
        RegistroLexema nuevoLex;
        if (lexema.contains(".")
                && lexChar[0] != '.'
                && Character.isDigit(lexChar[lexChar.length - 1])) {
            nuevoLex = new RegistroLexema(lexema, "Punto flotante", atributo);
            if (!MultiHerramientas.existeLexemaEnLista(regLexemas, nuevoLex)) {
                nuevoLex = MultiHerramientas.buscarDescripcion(nuevoLex);
                if(!MultiHerramientas.existeLexemaEnLista(regLexemas, nuevoLex)){
                   nuevoLex.setRepeticiones("1");
                   nuevoLex.setLinea(String.valueOf(this.posiciónFila)+",");
                    this.regLexemas.add(nuevoLex);
                    
               }
               else {
                   RegistroLexema aux = MultiHerramientas.existeLexemaEnLista(regLexemas, nuevoLex, estadoError);
                   int repeticiones, posicionLexema;
                   repeticiones = Integer.parseInt(aux.getRepeticiones())+1;
                   nuevoLex.setRepeticiones(String.valueOf(repeticiones));
                   nuevoLex.setLinea(aux.getLinea()+String.valueOf(this.posiciónFila)+",");
                   posicionLexema = MultiHerramientas.ExtraerPosicion(regLexemas, nuevoLex);
                   this.regLexemas.remove(posicionLexema);
                   this.regLexemas.add(posicionLexema, nuevoLex);
               }
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * Este método retorna el tipo de caracter en base al carácter recibido
     *
     * @param token Token a evaluar
     * @return El numero entero cómo banderín.
     */
    public int evaluadorCaracter(char token) {
        if (token != ' ') {
            if (Character.isLetter(token)) {
                return 0;
            } else if (Character.isDigit(token)) {
                return 1;
            } else if (token == ';' || token == '=' || token == '+'|| token == '-'
                    || token == '-' || token == '*' || token == '(' || token == ')'
                    || token == '{' || token == '}' || token == ','|| token == '/') {
                return 2;
            } else if (Character.isWhitespace(token)) {
                return -2;
            } else {
                return -1;
            }

        }
        return -1;
    }

    /**
     * ************************************************************************
     */
    /**
     * **************************Métodos de acceso*****************************
     */
    /**
     * ************************************************************************
     */
    public int getPosiciónColumna() {
        return posiciónColumna;
    }

    public int getPosiciónFila() {
        return posiciónFila;
    }

    public boolean isEstadoError() {
        return this.estadoError;
    }

    public ArrayList<RegistroLexema> getRegLexemas() {
        return regLexemas;
    }

    public ArrayList<RegistroError> getRegErrores() {
        return regErrores;
    }

}
