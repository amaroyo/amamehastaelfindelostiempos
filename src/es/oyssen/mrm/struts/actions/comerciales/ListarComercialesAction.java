package es.oyssen.mrm.struts.actions.comerciales;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


import es.oyssen.mrm.negocio.vo.CriterioComercialVO;
import es.oyssen.mrm.struts.actions.MrmAction;
import es.oyssen.mrm.util.UtilXML;

public class ListarComercialesAction extends MrmAction {

	
	@Override
	public ActionForward process(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {		
		
		CriterioComercialVO criterio = new CriterioComercialVO();
		String grupo = (String) request.getSession().getAttribute("usuarioIdGrupo");
		if (grupo.equals("2"))
			criterio.setIdCanal((String) request.getSession().getAttribute("usuarioIdCanal"));
		else if (grupo.equals("3")) 
			criterio.setIdDistribuidor((String) request.getSession().getAttribute("usuarioIdDistribuidor"));
		else if (grupo.equals("4")) 
			criterio.setIdComercial((String) request.getSession().getAttribute("usuarioIdComercial"));
		String xml =  UtilXML.buildXmlComboComerciales(getComercialesService().findByCriterio(criterio));
		
		response.setContentType("text/xml");
		ServletOutputStream sos = response.getOutputStream();
		sos.write(xml.getBytes("UTF-8"));
		sos.flush();
		sos.close();
		
		return mapping.findForward("success");
	}

}