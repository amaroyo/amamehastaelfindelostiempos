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
import es.oyssen.mrm.negocio.vo.InfoNombreTrabajoVO;
import es.oyssen.mrm.negocio.vo.PortafolioVO;
import es.oyssen.mrm.negocio.vo.ProfesorAsociadoVO;
import es.oyssen.mrm.negocio.vo.SeminarioAsignaturaVO;
import es.oyssen.mrm.negocio.vo.TrabajoDeCampoInfoVO;
import es.oyssen.mrm.negocio.vo.TrabajoDeCampoVO;
import es.oyssen.mrm.struts.actions.MrmAction;

public class DameAnyosAction extends MrmAction {

	@Override
	public ActionForward process(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			

		
		List<PortafolioVO> list = getPortafoliosService().findAnyos();
		
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
		sb.append("<portafolios>");
		if (o != null){
			List<PortafolioVO> portafolios = (List<PortafolioVO>) o;
			Iterator<PortafolioVO> it = portafolios.iterator();
			while(it.hasNext()) {
				sb.append("<portafolio>");
				PortafolioVO portafolio = it.next();
				
				sb.append("<nombre><![CDATA[" + portafolio.getAnyoAcademico() + "]]></nombre>");
				sb.append("</portafolio>");
			}	
		}
		sb.append("</portafolios>");
		return sb.toString();
	}

}
