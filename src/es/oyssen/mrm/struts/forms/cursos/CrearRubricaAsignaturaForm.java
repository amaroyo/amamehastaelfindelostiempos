package es.oyssen.mrm.struts.forms.cursos;

import java.util.HashMap;
import java.util.Map;

import es.oyssen.mrm.struts.forms.dhtmlx.DhtmlxGridForm;

public class CrearRubricaAsignaturaForm extends DhtmlxGridForm {

	private String nombre;
	private String competencias;
	private String anexo1;
	private Map values = new HashMap();
	
	
	/*<item type="input" name="notas_grupo_1" label="Nombre grupo 1" labelWidth="140" style="width:180" required="true"/>
		<item type="input" name="notas_criterio_1_1" label="Criterio 1" labelWidth="140" style="width:180" required="true"/>*/
	
	
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

	public Map getValues() {
		return values;
	}
    
    
	public void setValues(Map values) {
		this.values=values;
	}
    
	public void setValue(String key, Object value) {
        values.put(key, value);
    }

    public Object getValue(String key) {
        return values.get(key);
    }
    
    
}
