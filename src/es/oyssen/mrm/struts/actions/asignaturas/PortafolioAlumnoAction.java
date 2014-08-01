package es.oyssen.mrm.struts.actions.asignaturas;

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
import es.oyssen.mrm.negocio.vo.SeminarioAsignaturaVO;
import es.oyssen.mrm.negocio.vo.TrabajoDeCampoNombreVO;
import es.oyssen.mrm.negocio.vo.TrabajoDeCampoVO;
import es.oyssen.mrm.struts.actions.MrmAction;

public class PortafolioAlumnoAction extends MrmAction {

	@Override
	public ActionForward process(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			
		//String idUsuario = (String)request.getSession().getAttribute("idUsuario");
		String anyoAcademico = (String)request.getSession().getAttribute("anyoAcademico");
		String idAlumno = (String)request.getParameter("idAlumno");
		// para saber si es profesor o alumno
		//String usuarioIdGrupo = (String)request.getSession().getAttribute("idGrupoUsuario");
		
		PortafolioVO p = new PortafolioVO();
		String id_asignatura = (String)request.getParameter("idAsignatura");
		p.setIdAsignatura(id_asignatura);
		p.setAnyoAcademico(anyoAcademico);
		p.setIdAlumno(idAlumno);
		
		
		//***CUIDADO, HABRA Q CONSULTAR Q PROFESOR ES Y VER LOS TRABAJOS DE CAMPO DE DICHO PROFE/ASIGNATURA
		
		//"Super Admin" o "Coordinador" o "Virtual Tour"
		//if (usuarioIdGrupo.equals("1") || usuarioIdGrupo.equals("2") || usuarioIdGrupo.equals("5")) {
			p.setIdPortafolio((getPortafoliosService().findByAlumnoAsignatura(p)).getIdPortafolio());
		//}
		/*else
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
			}*/
		
		
		//application/json or application/xml text/html
		response.setContentType("text/xml;charset=utf-8");
		PrintWriter out;
	    out = response.getWriter();
	    out.print(parseXML(p));

		
		out.flush();
		out.close();
		return mapping.findForward("success");
	}
	
	
	public String parseXML(Object o) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("<portafolio>");
		if (o != null){
			PortafolioVO p = (PortafolioVO) o;
			
				sb.append("<id><![CDATA[" + p.getIdPortafolio()  + "]]></id>");
				
				
		}
		sb.append("</portafolio>");
		return sb.toString();
	}

}
