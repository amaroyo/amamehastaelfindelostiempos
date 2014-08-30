package es.oyssen.mrm.struts.actions.asignaturas;

import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import es.oyssen.mrm.negocio.vo.PortafolioVO;
import es.oyssen.mrm.negocio.vo.SeminarioAsignaturaVO;
import es.oyssen.mrm.negocio.vo.UsuarioPortafolioVO;
import es.oyssen.mrm.struts.actions.MrmAction;

public class DameAlumnosFaltanSeminarioAction extends MrmAction {

	@Override
	public ActionForward process(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			

		String idSeminario = (String) request.getParameter("idSeminario");
		String idAsignatura = (String) request.getParameter("idAsignatura");
		String anyoAcademico = (String) request.getSession().getAttribute("anyoAcademico");

		PortafolioVO p = new PortafolioVO();
		p.setAnyoAcademico(anyoAcademico);
		
		SeminarioAsignaturaVO sa = new SeminarioAsignaturaVO();
		sa.setIdSeminario(idSeminario);
		sa.setIdAsignatura(idAsignatura);
		
		List<UsuarioPortafolioVO> list = getSeminariosAsignaturaService().findAlumnosMissing(sa,p);
		
		response.setContentType("text/xml;charset=utf-8");
		PrintWriter out;
	    out = response.getWriter();
	    out.print(parseXML(list));

		
		out.flush();
		out.close();
		return mapping.findForward("success");
	}
	
	
	public String parseXML(Object o) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("<alumnos>");
		if (o != null){
			List<UsuarioPortafolioVO> portafolios = (List<UsuarioPortafolioVO>) o;
			Iterator<UsuarioPortafolioVO> it = portafolios.iterator();
			while(it.hasNext()) {
				sb.append("<portafolio>");
				UsuarioPortafolioVO portafolio = it.next();
				
				sb.append("<nombre><![CDATA[" + portafolio.getNombre() + " " + portafolio.getApellido1() + " " + portafolio.getApellido2() + "]]></nombre>");
				sb.append("<id><![CDATA[" + portafolio.getIdPortafolio() + "]]></id>");
				sb.append("</portafolio>");
			}	
		}
		sb.append("</alumnos>");
		return sb.toString();
	}

}
