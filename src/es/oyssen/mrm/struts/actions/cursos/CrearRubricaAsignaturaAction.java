package es.oyssen.mrm.struts.actions.cursos;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import es.oyssen.mrm.negocio.vo.AsignaturaVO;
import es.oyssen.mrm.negocio.vo.RubricaVO;
import es.oyssen.mrm.struts.actions.MrmAction;
import es.oyssen.mrm.struts.forms.cursos.CrearAsignaturaForm;
import es.oyssen.mrm.struts.forms.cursos.CrearRubricaAsignaturaForm;

public class CrearRubricaAsignaturaAction extends MrmAction {

	@Override
	public ActionForward process(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		CrearRubricaAsignaturaForm f = (CrearRubricaAsignaturaForm) form;
		AsignaturaVO asignatura = new AsignaturaVO();
		
		//application/json or application/xml text/html
		response.setContentType("text/xml;charset=utf-8");
		PrintWriter out;
	    out = response.getWriter();
	    
	    asignatura.setNombre(f.getNombre());
		asignatura = getAsignaturasService().findByNombre(asignatura);
				
		if (asignatura != null){
			
	        Map dynformValues = f.getValues();
	        dynformValues
	        for(int i =0; i<count ; i++){
	            String name = (String)dynformValues.get("name"+i);
	            String value = (String)dynformValues.get("value"+i);
	            System.out.println("Name:" + name + " Value:" + value);
	        }
	        return mapping.findForward("success");
			
			
			
			
			
			//grupos_rubrica
			value(notas_grupo_1)','<strong><bean:message key="label.nombre.grupo.criterios"/>'+' '+'1</strong>');
			formNewRubrica.setItemLabel('value(notas_criterio_1_1)
			
			
			//criterios_rubrica
			
			//rubrica
			RubricaVO rubrica = new RubricaVO();
			rubrica.setIdAsignatura(asignatura.getIdAsignatura());
			rubrica.setCompetencias(f.getCompetencias());
			rubrica.setAnexo(f.getAnexo1());
			rubrica.setNumeroCriterios(numero_criterios);
			getRubricasService().insert(rubrica);
			out.print("rúbrica no creada: no existe una asignatura con ese nombre");
		}
		else{
			/*
			getAsignaturasService().insert(asignatura);*/
			out.print("rubrica creada con éxito");
		}	
		out.flush();
		out.close();
		return mapping.findForward("success");
	}
}
