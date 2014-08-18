package es.oyssen.mrm.struts.forms.cursos;

import java.util.HashMap;
import java.util.Map;

import es.oyssen.mrm.struts.forms.dhtmlx.DhtmlxGridForm;

public class CrearRubricaAsignaturaForm extends DhtmlxGridForm {

	private String nombre;
	private String competencias;
	private String anexo1;
	private final Map values = new HashMap();
 
    public Map getValues(){
        return values;
    }
     
    public void setValue(String key, Object value){
        values.put(key, value);
    }
     
    public Object getValue(String key){
        return values.get(key);
    }
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCompetencias() {
		return competencias;
	}
	public void setCompetencias(String competencias) {
		this.competencias = competencias;
	}
	public String getAnexo1() {
		return anexo1;
	}
	public void setAnexo1(String anexo1) {
		this.anexo1 = anexo1;
	}
}
