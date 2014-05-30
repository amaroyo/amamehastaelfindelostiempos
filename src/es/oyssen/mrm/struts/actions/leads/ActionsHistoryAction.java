package es.oyssen.mrm.struts.actions.leads;

import es.oyssen.mrm.negocio.vo.ActionHistoryVO;
import es.oyssen.mrm.negocio.vo.ActionVO;
import es.oyssen.mrm.negocio.vo.LeadVO;
import es.oyssen.mrm.struts.actions.dhtmlx.DHTMLXFormAction;
import es.oyssen.mrm.struts.forms.dhtmlx.DhtmlxForm;
import es.oyssen.mrm.struts.forms.leads.ActionsHistoryForm;

public class ActionsHistoryAction extends DHTMLXFormAction {

	@Override
	public Object load(DhtmlxForm f) throws Exception {
		return null;
	}

	@Override
	public String save(DhtmlxForm f) throws Exception {
		ActionsHistoryForm form = (ActionsHistoryForm) f;
		ActionHistoryVO action = new ActionHistoryVO();
		ActionVO act = new ActionVO();
		act.setIdAction(form.getAction());
		action.setAction(act);
		String comments = form.getComments();
		if (!"undefined".equals(comments)) {
			action.setComments(comments);
		}
		LeadVO lead = new LeadVO();
		lead.setIdLead(form.getIdLead());

		getLeadsService().addAction(lead, action);
		getLeadsService().updateFechaModificacion(lead);
		return null;
	}

	@Override
	public String parseXML(Object o) throws Exception {
		StringBuffer sb = new StringBuffer();
		return sb.toString();
	}

	@Override
	public String create(DhtmlxForm f) throws Exception {
		// TODO Auto-generated method stub
		return null;
		
	}

}
