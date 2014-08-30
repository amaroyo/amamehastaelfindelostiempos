package es.oyssen.mrm.struts.actions.cursos;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import es.oyssen.mrm.negocio.vo.SeminarioAsignaturaVO;
import es.oyssen.mrm.struts.actions.MrmAction;

public class CheckCodigoSeminarioAction extends MrmAction {

	@Override
	public ActionForward process(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			

		
		SeminarioAsignaturaVO s = new SeminarioAsignaturaVO();
		String codigo = (String)request.getParameter("codigo");
		s.setCodigo(codigo);
		
		
		s =  getSeminariosAsignaturaService().findByCodigo(s);
		
		response.setContentType("text/xml;charset=utf-8");
		PrintWriter out;
	    out = response.getWriter();
	    out.print(parseXML(s));

		
		out.flush();
		out.close();
		return mapping.findForward("success");
	}
	
	
	public String parseXML(Object o) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("<seminarios>");
		String existe = "existe";
		if (o != null){
			sb.append("<seminario>");				
			sb.append("<check><![CDATA[" + existe + "]]></check>");	
			sb.append("</seminario>");
				
		}
		else {
			existe="noexiste";
			sb.append("<seminario>");				
			sb.append("<check><![CDATA[" + existe + "]]></check>");	
			sb.append("</seminario>");
		}
		sb.append("</seminarios>");
		return sb.toString();
	}

}
