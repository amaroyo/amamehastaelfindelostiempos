package es.oyssen.mrm.struts.actions.cursos;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import es.oyssen.mrm.negocio.vo.AsignaturaVO;
import es.oyssen.mrm.struts.actions.MrmAction;
import es.oyssen.mrm.struts.forms.cursos.CrearAsignaturaForm;
import es.oyssen.mrm.struts.forms.cursos.CrearRubricaAsignaturaForm;

public class CrearRubricaAsignaturaAction extends MrmAction {

	@Override
	public ActionForward process(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		CrearRubricaAsignaturaForm f = (CrearRubricaAsignaturaForm) form;
		AsignaturaVO asignaturaIN = new AsignaturaVO();
		AsignaturaVO asignaturaOUT = new AsignaturaVO();
		
		//application/json or application/xml text/html
		response.setContentType("text/xml;charset=utf-8");
		PrintWriter out;
	    out = response.getWriter();
	    
	    asignaturaIN.setNombre(f.getNombre());
		asignaturaOUT = getAsignaturasService().findByNombre(asignaturaIN);
				
		if (asignaturaOUT != null)
			out.print("r�brica no creada: no existe una asignatura con ese nombre");
		else{
			/*
			getAsignaturasService().insert(asignatura);*/
			out.print("rubrica creada con �xito");
		}	
		out.flush();
		out.close();
		return mapping.findForward("success");
	}
}
