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

public class BuscarAsignaturaAction extends MrmAction {

	@Override
	public ActionForward process(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		CrearAsignaturaForm f = (CrearAsignaturaForm) form;
		AsignaturaVO asignaturaBuscar = new AsignaturaVO();
		AsignaturaVO asignaturaDevolver = new AsignaturaVO();
		
		//application/json or application/xml text/html
		response.setContentType("text/xml;charset=utf-8");
		PrintWriter out;
	    out = response.getWriter();
	    
	    //if(!StringUtil.isNullOrBlank(f.getNombre())){
	    	asignaturaBuscar.setNombre(f.getNombre());
	    	asignaturaDevolver = getAsignaturasService().findByNombre(asignaturaBuscar);
			if(asignaturaDevolver != null) {
				out.print("Ya existe una asignatura con ese nombre:\n");
				out.print("Nombre: " + asignaturaDevolver.getNombre() + "\n");
				out.print("Código: " + asignaturaDevolver.getCodigo() + "\n");
				out.print("Curso: " + asignaturaDevolver.getCurso() + "\n");
			}
			else{
				out.print("No existe ninguna asignatura con ese nombre");
			}
		//}
		out.flush();
		out.close();
		return mapping.findForward("success");
	}
}
