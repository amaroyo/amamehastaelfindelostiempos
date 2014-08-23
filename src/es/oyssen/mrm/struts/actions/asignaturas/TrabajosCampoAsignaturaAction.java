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
import es.oyssen.mrm.negocio.vo.InfoNombreTrabajoVO;
import es.oyssen.mrm.negocio.vo.PortafolioVO;
import es.oyssen.mrm.negocio.vo.ProfesorAsociadoVO;
import es.oyssen.mrm.negocio.vo.SeminarioAsignaturaVO;
import es.oyssen.mrm.negocio.vo.TrabajoDeCampoInfoVO;
import es.oyssen.mrm.negocio.vo.TrabajoDeCampoVO;
import es.oyssen.mrm.struts.actions.MrmAction;

public class TrabajosCampoAsignaturaAction extends MrmAction {

	@Override
	public ActionForward process(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			
		
		
		
		
		String anyoAcademico = (String)request.getSession().getAttribute("anyoAcademico");
		
		
		
		PortafolioVO p = new PortafolioVO();
		String id_asignatura = (String)request.getParameter("idAsignatura");
		p.setIdAsignatura(id_asignatura);
		p.setAnyoAcademico(anyoAcademico);
		List<InfoNombreTrabajoVO> trabajos = null;
		
	
		trabajos = getTrabajosDeCampoService().findAllNombresByAsignatura(p);
		
		response.setContentType("text/xml;charset=utf-8");
		PrintWriter out;
	    out = response.getWriter();
	    out.print(parseXML(trabajos));

		
		out.flush();
		out.close();
		return mapping.findForward("success");
	}
	
	
	public String parseXML(Object o) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("<trabajos>");
		if (o != null){
			List<InfoNombreTrabajoVO> trabajos = (List<InfoNombreTrabajoVO>) o;
			Iterator<InfoNombreTrabajoVO> it = trabajos.iterator();
			while(it.hasNext()) {
				sb.append("<trabajo>");
				InfoNombreTrabajoVO trabajo = it.next();
				sb.append("<id><![CDATA[" + trabajo.getIdTrabajoInfo() + "]]></id>");
				sb.append("<nombre><![CDATA[" + trabajo.getNombre()  + "]]></nombre>");
				sb.append("</trabajo>");
			}	
		}
		sb.append("</trabajos>");
		return sb.toString();
	}

}
