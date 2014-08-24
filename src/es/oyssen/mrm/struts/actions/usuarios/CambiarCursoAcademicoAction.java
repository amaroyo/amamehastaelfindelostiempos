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
import es.oyssen.mrm.negocio.vo.SeminarioAsignaturaVO;
import es.oyssen.mrm.negocio.vo.TrabajoDeCampoInfoVO;
import es.oyssen.mrm.negocio.vo.TrabajoDeCampoVO;
import es.oyssen.mrm.struts.actions.MrmAction;

public class CambiarCursoAcademicoAction extends MrmAction {

	@Override
	public ActionForward process(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			
		
		String nuevoAnyoAcademico = (String)request.getParameter("anyo");
		
		request.getSession().setAttribute("anyoAcademico", nuevoAnyoAcademico);
		
		
		
		response.setContentType("text/xml;charset=utf-8");
		PrintWriter out;
	    out = response.getWriter();
	    out.print(parseXML("exito"));

		
		out.flush();
		out.close();
		return mapping.findForward("success");
	}
	
	
	public String parseXML(Object o) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("<cambio>");
		
		if (o != null){
			String t = (String) o;			
			sb.append("<nombre><![CDATA[" + t  + "]]></nombre>");
			
		}
		
		sb.append("</cambio>");
		return sb.toString();
	}

}
