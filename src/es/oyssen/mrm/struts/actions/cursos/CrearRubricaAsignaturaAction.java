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

public class CrearRubricaAsignaturaAction extends MrmAction {

	@Override
	public ActionForward process(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		CrearAsignaturaForm f = (CrearAsignaturaForm) form;
		AsignaturaVO asignatura = new AsignaturaVO();
		
		//application/json or application/xml text/html
		response.setContentType("text/xml;charset=utf-8");
		PrintWriter out;
	    out = response.getWriter();
	    
	    asignatura.setNombre(f.getNombre());
		
		if (getAsignaturasService().findByNombre(asignatura) != null)
			out.print("asignatura no creada: ya existe una asignatura con ese nombre");
		else{
			asignatura.setNombre(f.getNombre());
			asignatura.setCodigo(f.getCodigo());
			asignatura.setCurso(f.getCurso());
			asignatura.setDescripcion(f.getDescripcion());
			getAsignaturasService().insert(asignatura);
			out.print("asignatura creada con éxito");
		}	
		out.flush();
		out.close();
		return mapping.findForward("success");
	}
}
