package es.oyssen.mrm.util;


public class Util {

	private Util() {
	}
	
	/**
	 * Convierte el texto que se pasa como argumento a una cadena de texto
	 * en formato HTML
	 * @param texto Texto a procesar
	 * @return Texto en formato HTML
	 */
	public static String textToHTML(String texto) {
		return (texto != null) ? texto.replaceAll("\n", "<br />") : "";
	}
}
