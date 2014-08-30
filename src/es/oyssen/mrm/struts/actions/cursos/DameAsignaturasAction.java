package es.oyssen.mrm.struts.actions.cursos;

import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import es.oyssen.mrm.negocio.vo.AsignaturaVO;
import es.oyssen.mrm.struts.actions.MrmAction;

public class DameAsignaturasAction extends MrmAction {

	@Override
	public ActionForward process(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			

		String anyoAcademico = (String)request.getSession().getAttribute("anyoAcademico");
		List<AsignaturaVO> list =  getAsignaturasService().findAll(anyoAcademico);
		
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
		sb.append("<asignaturas>");
		if (o != null){
			List<AsignaturaVO> asignaturas = (List<AsignaturaVO>) o;
			Iterator<AsignaturaVO> it = asignaturas.iterator();
			while(it.hasNext()) {
				sb.append("<asignatura>");
				AsignaturaVO asignatura = it.next();
				sb.append("<id><![CDATA[" + asignatura.getIdAsignatura() + "]]></id>");
				sb.append("<nombre><![CDATA[" + asignatura.getCodigo() + " - "+ asignatura.getNombre()  + "]]></nombre>");
				sb.append("</asignatura>");
			}	
		}
		sb.append("</asignaturas>");
		return sb.toString();
	}

}
