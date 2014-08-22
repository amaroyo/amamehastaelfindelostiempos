package es.oyssen.mrm.struts.forms.asignaturas;

import java.util.HashMap;
import java.util.Map;

import es.oyssen.mrm.struts.forms.dhtmlx.DhtmlxGridForm;

public class NotasRubricaForm extends DhtmlxGridForm {

	private String idPortafolio;
	private String idAsignatura;
	private Map values = new HashMap();
	
	
	
	public String getIdPortafolio() {
		return idPortafolio;
	}

	public void setIdPortafolio(String idPortafolio) {
		this.idPortafolio = idPortafolio;
	}

	public Map getValues(){
	    return values;
	}
	
	public void setValues(Map values){
		this.values = values;
	}
	 
	public void setValue(String key, Object value){
	    values.put(key, value);
	}
	 
	public Object getValue(String key){
	    return values.get(key);
	}

	public String getIdAsignatura() {
		return idAsignatura;
	}

	public void setIdAsignatura(String idAsignatura) {
		this.idAsignatura = idAsignatura;
	}
	
}
