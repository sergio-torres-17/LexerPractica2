package Herramientas;

import Objetos.ObjDirectorio;
import Objetos.RegistroError;
import Objetos.RegistroLexema;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class MultiHerramientas {

    public static boolean existeLexemaEnLista(ArrayList<RegistroLexema> listaLex, RegistroLexema lexema) {
        if (listaLex.size() > 0) {
            for (RegistroLexema registroLexema : listaLex) {
                if (registroLexema != null) {
                    if (registroLexema.getLexema().equals(lexema.getLexema())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static RegistroLexema existeLexemaEnLista(ArrayList<RegistroLexema> listaLex, RegistroLexema lexema, boolean evaluar) {
        if (listaLex.size() > 0) {
            for (RegistroLexema registroLexema : listaLex) {
                if (registroLexema != null) {
                    if (registroLexema.getLexema().equals(lexema.getLexema()) && registroLexema.getClasificación().equals(lexema.getClasificación())) {
                        return registroLexema;
                    }
                }
            }
        }
        return null;
    }

    public static boolean existeErrorEnLista(ArrayList<RegistroError> errores, RegistroError error) {
        if (errores.size() > 0) {
            for (RegistroError error_ : errores) {
                if (error_.getLexema().equals(error.getLexema())) {
                    return true;
                }
            }
        }
        return false;
    }

    public static String maxAttrLexema(ArrayList<RegistroLexema> listaLex) {
        String maxNum = "500";
        int auxSum = 0;
        for (RegistroLexema registroLexema : listaLex) {
            if (registroLexema != null) {
                if (registroLexema.getClasificación().equals("Identificador")) {
                    auxSum = Integer.parseInt(registroLexema.getAtributo()) + 1;
                    maxNum = String.valueOf(auxSum);
                }
            }
        }
        return maxNum;
    }

    public static String buscarAtributoPorClasificación(ArrayList<RegistroLexema> listaLex, String clasificación) {
        if (listaLex.size() > 0) {
            for (RegistroLexema registroLexema : listaLex) {
                if (registroLexema != null) {
                    if (registroLexema.getClasificación().equals(clasificación)) {
                        return registroLexema.getAtributo();
                    }
                }
            }
        }
        return maxAttrLexema(listaLex);
    }

    public static DefaultTableModel contruirTablaLexemas(ArrayList<RegistroLexema> regLex) {
        DefaultTableModel dev = new DefaultTableModel();
        dev.addColumn("Lexema");
        dev.addColumn("Token");
        dev.addColumn("Descripción");
        dev.addColumn("Valor");
        dev.addColumn("Repeticiones");
        dev.addColumn("Linea");
        for (RegistroLexema registroLexema : regLex) {
            if (registroLexema != null) {
                if(registroLexema.getClasificación().equals("Identificador")||registroLexema.getClasificación().equals("Enteros")||registroLexema.getClasificación().equals("Punto flotante")){
                    dev.addRow(new String[]{registroLexema.getLexema(), registroLexema.getClasificación(), 
                    registroLexema.getDescripcion(), String.valueOf(registroLexema.getAtributo()), 
                    registroLexema.getRepeticiones(), registroLexema.getLinea().substring(0, registroLexema.getLinea().length()-1)});
                }
            }
        }
        return dev;
    }
    public static boolean esNuemeroEntero(String valor) {
        try {
            Integer.parseInt(valor);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public static RegistroLexema buscarDescripcion(RegistroLexema rL) {
        RegistroLexema dev = null;
        for (ObjDirectorio obj : Colecciones.DIRECTORIO_LEXEMA) {
            if (rL.getClasificación().equals("Punto flotante") || rL.getClasificación().equals("Enteros")) {
                if (obj.getLexema().equals(rL.getClasificación())) {
                    dev = rL;
                    dev.setDescripcion(obj.getDescripción());
                    dev.setAtributo(dev.getLexema());
                    return dev;
                }
            } else if (rL.getClasificación().equals("Identificador")) {
                if (obj.getLexema().equals(rL.getClasificación())) {
                    dev = rL;
                }
            } else if (rL.getClasificación().toLowerCase().equals("carácter simple")) {
                if (obj.getLexema().length() == 1) {
                    if (obj.getLexema().toCharArray()[0] == rL.getLexema().toCharArray()[0]) {
                        dev = rL;
                        dev.setDescripcion(obj.getDescripción());
                        return dev;
                    }
                }
            } else {
                if (obj.getLexema().equals(rL.getLexema())) {
                    dev = rL;
                    dev.setDescripcion(obj.getDescripción());
                    return dev;
                }
            }
        }
        return dev;
    }

    public static boolean EvaluarSiEsDeclarador(String lexema) {
        for (ObjDirectorio registro : Colecciones.DIRECTORIO_LEXEMA) {
            if (registro.getDescripción().equals("Tipo de dato")) {
                if (registro.getLexema().equals(lexema)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public static int ExtraerPosicion (ArrayList <RegistroLexema> lista, RegistroLexema lexema){
        for (int i = 0; i < lista.size(); i++) {
            if(lista.get(i).getLexema().equals(lexema.getLexema())){
                return i;
            }
        }
        return 0;
    }
    
}