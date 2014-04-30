package es.oyssen.mrm.struts.forms;

import java.lang.reflect.Field;

import org.apache.struts.action.ActionForm;

public abstract class BaseActionForm extends ActionForm{

	
	public String generateXML() throws IllegalArgumentException, IllegalAccessException{
		StringBuffer xml = new StringBuffer();

		Class c = this.getClass();
		Field[] atributos = c.getDeclaredFields();
		for (int i = 0; i < atributos.length; i++) {
			atributos[i].setAccessible(true);
			String strNombreAtributo = atributos[i].getName();
			Object valor = atributos[i].get(this);
			if(valor == null)
				xml.append("<"+strNombreAtributo+"></"+strNombreAtributo+">");
			else
				xml.append("<"+strNombreAtributo+">"+valor+"</"+strNombreAtributo+">");
				
		}
		return xml.toString();
	}
}
