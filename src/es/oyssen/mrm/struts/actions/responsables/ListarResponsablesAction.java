package es.oyssen.mrm.struts.actions.responsables;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import es.oyssen.mrm.negocio.vo.CriterioResponsableVO;
import es.oyssen.mrm.struts.actions.MrmAction;
import es.oyssen.mrm.util.UtilXML;

public class ListarResponsablesAction extends MrmAction {

	
	@Override
	public ActionForward process(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {		
		
		CriterioResponsableVO criterio = new CriterioResponsableVO();
		String xml =  UtilXML.buildXmlComboResponsables(getResponsablesService().findByCriterio(criterio));
		
		response.setContentType("text/xml");
		ServletOutputStream sos = response.getOutputStream();
		sos.write(xml.getBytes("UTF-8"));
		sos.flush();
		sos.close();
		
		return mapping.findForward("success");
	}

}