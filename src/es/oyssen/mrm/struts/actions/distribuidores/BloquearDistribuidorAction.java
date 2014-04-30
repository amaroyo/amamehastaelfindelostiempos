package es.oyssen.mrm.struts.actions.distribuidores;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import es.oyssen.mrm.negocio.vo.DistribuidorVO;
import es.oyssen.mrm.struts.actions.MrmAction;
import es.oyssen.mrm.struts.forms.distribuidores.DistribuidorForm;

public class BloquearDistribuidorAction extends MrmAction {

	
	@Override
	public ActionForward process(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {		
		
		DistribuidorForm f = (DistribuidorForm) form;
		DistribuidorVO distribuidor = new DistribuidorVO();
		distribuidor.setIdDistribuidor(f.getIdDistribuidor());		
		getDistribuidoresService().bloquear(distribuidor);
		
		return mapping.findForward("success");
	}

}