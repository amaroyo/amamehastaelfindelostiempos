package es.oyssen.mrm.struts.actions.asignaturas;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import es.oyssen.mrm.negocio.vo.SeminarioRealizadoVO;
import es.oyssen.mrm.struts.actions.MrmAction;

public class ActualizarSeminarioAlumnoAction extends MrmAction {

	@Override
	public ActionForward process(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			
		
		
		
		String idSeminario = (String)request.getParameter("idSeminario");
		String idPortafolio = (String)request.getParameter("idPortafolio");

		SeminarioRealizadoVO sa = new SeminarioRealizadoVO();
		sa.setIdPortafolio(idPortafolio);
		sa.setIdSeminario(idSeminario);
		
		getSeminariosRealizadosService().insert(sa);
		
		return mapping.findForward("success");
	}
	
	
	public String parseXML(Object o) throws Exception {
		/*StringBuffer sb = new StringBuffer();
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
		sb.append("</trabajos>");*/
		return "";
	}

}
