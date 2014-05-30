package es.oyssen.mrm.test;

import es.oyssen.mrm.util.ParseHTML;

public class Test {
	public static void main(String[] args) {
		String texto = "estoy siguiendo a Guía Repsol http://t.co/plsQCWrn";
//		String pattern = "^(ht|f)tp(s?)\\:\\/\\/[0-9a-zA-Z]([-.\\w]*[0-9a-zA-Z])*(:(0-9)*)*(\\/?)( [a-zA-Z0-9\\-\\.\\?\\,\\’\\/\\\\+&%\\$#_]*)?$";
//		String pattern2 = "^(www.)[0-9a-zA-Z]([-.\\w]*[0-9a-zA-Z])*(:(0-9)*)*(\\/?)( [a-zA-Z0-9\\-\\.\\?\\,\\’\\/\\\\+&%\\$#_]*)?$";
//		
//		String pattern3 = "^((ht|f)tp(s?)\\:\\/\\/)(www.)[0-9a-zA-Z]([-.\\w]*[0-9a-zA-Z])*(:(0-9)*)*(\\/?)( [a-zA-Z0-9\\-\\.\\?\\,\\’\\/\\\\+&%\\$#_]*)?$";
		
		String html = ParseHTML.parseStringToHTML(texto);
		
		
		
		System.out.println(texto);
		System.out.println(html);
		
	}
}
