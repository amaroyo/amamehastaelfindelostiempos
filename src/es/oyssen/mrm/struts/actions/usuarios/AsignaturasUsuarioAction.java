package es.oyssen.mrm.struts.actions.usuarios;

import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import es.oyssen.mrm.negocio.vo.AsignaturaVO;
import es.oyssen.mrm.negocio.vo.PortafolioVO;
import es.oyssen.mrm.negocio.vo.ProfesorAsociadoVO;
import es.oyssen.mrm.struts.actions.MrmAction;

public class AsignaturasUsuarioAction extends MrmAction {

	@Override
	public ActionForward process(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			
		String idUsuario = (String)request.getSession().getAttribute("idUsuario");
		String anyoAcademico = (String)request.getSession().getAttribute("anyoAcademico");
		
		// para saber si es profesor o alumno
		String usuarioIdGrupo = (String)request.getSession().getAttribute("usuarioIdGrupo");
		List<AsignaturaVO> asignaturas = null;
		
		//"Super Admin" o "Coordinador" o "Virtual Tour"
		if (usuarioIdGrupo.equals("1") || usuarioIdGrupo.equals("2") || usuarioIdGrupo.equals("5")) {
				asignaturas = getAsignaturasService().findAll(anyoAcademico);
		}
		else
			// "Profesor"
			if (usuarioIdGrupo.equals("3")) {
				ProfesorAsociadoVO profesor = new ProfesorAsociadoVO();
				profesor.setIdProfesor(idUsuario);
				profesor.setAnyoAcademico(anyoAcademico);
				asignaturas = getAsignaturasService().findByProfesorAnyoAcademico(profesor);
			}
		else
			// "Alumno"
			if (usuarioIdGrupo.equals("4")) {
				PortafolioVO portafolio = new PortafolioVO();
				portafolio.setIdAlumno(idUsuario);
				portafolio.setAnyoAcademico(anyoAcademico);
				asignaturas = getAsignaturasService().findByAlumnoAnyoAcademico(portafolio);
			}
		
		
		//application/json or application/xml text/html
		response.setContentType("text/xml;charset=utf-8");
		PrintWriter out;
	    out = response.getWriter();
	    out.print(parseXML(asignaturas));

		
		out.flush();
		out.close();
		return mapping.findForward("success");
	}
	
	
	public String parseXML(Object o) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("<asignaturas>");
		if (o != null){
			List<AsignaturaVO> asignaturas = (List<AsignaturaVO>) o;
			Iterator<AsignaturaVO> it = asignaturas.iterator();
			while(it.hasNext()) {
				sb.append("<asignatura>");
				AsignaturaVO asignatura = it.next();
				sb.append("<id><![CDATA[" + asignatura.getIdAsignatura() + "]]></id>");
				sb.append("<nombre><![CDATA[" + asignatura.getCodigo()+" - "+asignatura.getNombre() + "]]></nombre>");
				sb.append("</asignatura>");
			}	
		}
		sb.append("</asignaturas>");
		return sb.toString();
	}

}
