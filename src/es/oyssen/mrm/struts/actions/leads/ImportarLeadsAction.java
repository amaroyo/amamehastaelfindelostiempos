package es.oyssen.mrm.struts.actions.leads;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import es.oyssen.mrm.negocio.vo.MarketingActivityVO;
import es.oyssen.mrm.struts.actions.MrmAction;
import es.oyssen.mrm.struts.forms.leads.ImportarLeadsForm;

public class ImportarLeadsAction extends MrmAction {

	@Override
	public ActionForward process(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ImportarLeadsForm f = (ImportarLeadsForm) form;
		MarketingActivityVO ma = new MarketingActivityVO();
		ma.setIdMarketingActivity(f.getIdMarketingActivity());
		
		getLeadsService().process(f.getFichero().getInputStream(), f.getCanal());
		
		return mapping.findForward("success");
	}

}
