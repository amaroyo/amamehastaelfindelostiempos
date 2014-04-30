package es.oyssen.mrm.util;

public class DiagramasUtil {
	public static String parseAttribute(String attribute) {
		return attribute.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
	}
	
	public static void main(String[] args) {
		System.out.println(parseAttribute("<hola>"));
	}
}
