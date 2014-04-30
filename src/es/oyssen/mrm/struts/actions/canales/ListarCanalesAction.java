package es.oyssen.mrm.struts.actions.canales;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import es.oyssen.mrm.negocio.vo.CriterioCanalVO;
import es.oyssen.mrm.struts.actions.MrmAction;
import es.oyssen.mrm.util.UtilXML;

public class ListarCanalesAction extends MrmAction {

	
	@Override
	public ActionForward process(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {		
		
		CriterioCanalVO criterio = new CriterioCanalVO();
		String grupo = (String) request.getSession().getAttribute("usuarioIdGrupo");
		if (!grupo.equals("1")) 
			criterio.setIdCanal((String) request.getSession().getAttribute("usuarioIdCanal"));
		String xml =  UtilXML.buildXmlComboCanales(getCanalesService().findByCriterio(criterio));
		
		response.setContentType("text/xml");
		ServletOutputStream sos = response.getOutputStream();
		sos.write(xml.getBytes("UTF-8"));
		sos.flush();
		sos.close();
		
		return mapping.findForward("success");
	}

}