/*
 * StringUtil.java
 *
 * Created on 13 de septiembre de 2007
 */

package es.oyssen.mrm.util;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class StringUtil {
    
    /** Contructor privado para evitar multiples instancias de la clase. */
    private StringUtil() {
    }
    
    /**
     * Comprueba si una cadena es nula o cadena vacia.
     * @param value Cadena a comprobar.
     * @return true si la cadena es nula o vacia, false en caso contrario.
     */
    public static final boolean isNullOrBlank(String value) {
        return value == null || "undefined".equals(value) || "null".equals(value) || value.trim().length() == 0;
    }
    
    
    /**
     * Comprueba si una cadena es nula y la convierte a cadena vacia.
     * @param value Cadena a convertir.
     * @return Cadena vacia si el valor pasado es null, sino el valor pasado..
     */
    public static final String nullToString(String value) {
        return ((value == null || "undefined".equals(value) || "null".equals(value)) ? "" : value);
    }
    
    
    public static final String recortaTexto(String value,int tam) {
    	String resultado = value;
    	if(!isNullOrBlank(resultado)){
    		resultado = resultado.replaceAll(">","&gt;");
    		resultado = resultado.replaceAll("<","&lt;");
    		System.out.println("*******************************************");
    		System.out.println(resultado);
    		System.out.println("*******************************************");
    	} else{
    		System.out.println("2*******************************************");
    		System.out.println(resultado);
    		System.out.println("2*******************************************");
    		resultado = "";
    	}
        if(resultado.length() > tam){
        	String aux = resultado.substring(0,tam) + " ...";
        	System.out.println("*******************************************");
    		System.out.println(aux);
    		System.out.println("*******************************************");
        	return aux;
        }
        return nullToString(resultado);
    }
    
    
    public static final String eliminaHTML(String value) {
    	String resultado = value;
    	if(!isNullOrBlank(resultado)){
    		resultado = resultado.replaceAll(">","&gt;");
    		resultado = resultado.replaceAll("<","&lt;");
    		resultado = resultado.replaceAll("'","&rdquo;");
    		resultado = resultado.replaceAll("\"","&rdquo;");
    	} else{
    		resultado = "";
    	}
    	return resultado;
    }
    
    
    public static final String rellenarIzquierda(String cadena, String caracter, int longitud) {
        String resultado = cadena;
        if (cadena != null) {
            for (int i = cadena.length(); i < longitud; i++) {
                resultado = caracter + resultado;
            }
        }
        return resultado;
    }
    
    
    public static final String convertirAString(int entero){
    	return String.valueOf(entero);
    }
    
    
    public static final String almacenarUnicoEspacio(String value){
    	if (value != null && !value.equals("")){
    		value = value.trim();
    		Pattern p = Pattern.compile("\\b\\s{2,}\\b", Pattern.MULTILINE);//con el trim quita espacios por delante y por detras y usa el patron palabra antes y despues y whitespace dos como mínimo (incluye espacio, tab y salto de linea)
    		Matcher m = p.matcher(value);
    		value = m.replaceAll(" ");
    	}
    	
    	return value;
    }
    
    public static final String limpiarTexto(String value){
    	if (value != null && !value.equals("")){
    		value = value.trim();
    		Pattern p = Pattern.compile("\\n{1,}", Pattern.MULTILINE); //quitar saltos de linea que aparecen seguidos
    		Matcher m = p.matcher(value);
    		value = m.replaceAll("\n");
    		
    		p = Pattern.compile("^\\s{2,}$", Pattern.MULTILINE); //quitar lineas en blanco
    		m = p.matcher(value);
    		value = m.replaceAll("");
    	}
    	
    	return value;
    }
    
    public static boolean isNumeric(String cadena){
    	try {
    		Integer.parseInt(cadena);
    		return true;
    	} catch (NumberFormatException nfe){
    		return false;
    	}
    }
    
	public static String parseForIntermedia(String texto) {
		String contenido = new String(texto);
		String intermedia = "";
		contenido = contenido.replaceAll(",", " ").replaceAll("-", " ").replaceAll("\\+", "");
		StringTokenizer tokens = new StringTokenizer(contenido, " ");

		while (tokens.hasMoreElements()) {
			String t = (String) tokens.nextElement();
			intermedia += "{" + t + "}" + " AND ";
		}
		if (intermedia.endsWith(" AND ")) {
			intermedia = intermedia.substring(0, intermedia.lastIndexOf(" AND "));
		}		
		return intermedia;
	}
	
	// Método para eliminar fechas del contenido de un documento. Se validan ciertos patrones de horas y fechas que se podrían enriquecer.
	public static String quitarFechas(String texto) {
		GregorianCalendar hoy = new GregorianCalendar();
		int year4 = hoy.get(Calendar.YEAR);
		String retorno = StringUtil.nullToString(texto);
		retorno = retorno.replaceAll("[0-1][0-9]:[0-5][0-9]", "");
		retorno = retorno.replaceAll("[0-3][0-9](-|/)[0-1][0-9](-|/)("+year4+"|"+year4%100+")","");
		return retorno;
	}
	
	/**
     * Recorta un String a partir de una subcadena. Elimina la subcadena pasada como párametro
     * del String principal. Si la subcadena no existe dentro del String, el resultado es el
     * propio String. 
     * @param subcadena - String: La subcadena a eliminar de la cadena objeto.
     * @param cadena - String: El String donde se eliminará la subcadena.
     * @return String: El String resultante, sin la subcadena.
     */
    public static final String eliminaSubcadena(String subcadena, String cadena) {
    	String result = subcadena;
        if (!isNullOrBlank(result)) {
        	if (!(cadena.indexOf(subcadena) < 0)) {
        		result = cadena.replace(subcadena, "");
        	}
        }
        return result;
    }
    
}