package es.oyssen.mrm.struts.actions.usuarios;

import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import es.oyssen.mrm.negocio.exceptions.ServiceException;
import es.oyssen.mrm.negocio.vo.AsignaturaVO;
import es.oyssen.mrm.negocio.vo.ErrorLogVO;
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
		
		String grupo = (String) request.getSession().getAttribute("idGrupoUsuario");
		if(!grupo.equals("1") && anyosDistintos(nuevoAnyoAcademico)) request.getSession().setAttribute("anyoActual", "falso");
		if (!anyosDistintos(nuevoAnyoAcademico)) request.getSession().setAttribute("anyoActual", "verdadero");
		
		
		response.setContentType("text/xml;charset=utf-8");
		PrintWriter out;
	    out = response.getWriter();
	    out.print(parseXML("exito"));

		
		out.flush();
		out.close();
		return mapping.findForward("success");
	}
	
	
	private boolean anyosDistintos(String nuevoAnyoAcademico) {
		
		ErrorLogVO e = new ErrorLogVO();
		e.setIdError("1");
		String act="";
		
		try {
			e = getErroresLogService().findAnyoAcademico(e);
			String fecha = e.getFecha();
			String[] sp = fecha.split(" ");
			String[] s = sp[0].split("-");
			act= s[0] + "/" + Integer.toString(Integer.parseInt(s[0])+1);
		} catch (ServiceException e1) {
			String actual = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
			String pasado = String.valueOf(Calendar.getInstance().get(Calendar.YEAR)-1);
			act= pasado + "/" + actual;
		}
		return !act.equals(nuevoAnyoAcademico);
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
