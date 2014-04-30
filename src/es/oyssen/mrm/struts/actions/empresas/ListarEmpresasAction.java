package es.oyssen.mrm.struts.actions.empresas;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import es.oyssen.mrm.negocio.vo.CriterioEmpresaVO;
import es.oyssen.mrm.struts.actions.MrmAction;
import es.oyssen.mrm.util.UtilXML;

public class ListarEmpresasAction extends MrmAction {

	
	@Override
	public ActionForward process(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {		
		
		CriterioEmpresaVO criterio = new CriterioEmpresaVO();
		String xml =  UtilXML.buildXmlComboEmpresas(getEmpresasService().findByCriterio(criterio));		
		
		response.setContentType("text/xml");
		ServletOutputStream sos = response.getOutputStream();
		sos.write(xml.getBytes("UTF-8"));
		sos.flush();
		sos.close();
		
		
		return mapping.findForward("success");
	}

}