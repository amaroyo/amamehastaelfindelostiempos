package es.oyssen.mrm.struts.actions.leads;

import java.util.List;

import es.oyssen.mrm.negocio.vo.ActionHistoryVO;
import es.oyssen.mrm.negocio.vo.LeadVO;
import es.oyssen.mrm.struts.actions.dhtmlx.DHTMLXGridAction;
import es.oyssen.mrm.struts.forms.dhtmlx.DhtmlxGridForm;
import es.oyssen.mrm.struts.forms.leads.GridActionsHistoryForm;
import es.oyssen.mrm.util.UtilXML;

public class GridActionsHistoryAction extends DHTMLXGridAction {

	@Override
	public String search(DhtmlxGridForm f) throws Exception {
		GridActionsHistoryForm form = (GridActionsHistoryForm) f;		
		LeadVO lead = new LeadVO();
		lead.setIdLead(form.getIdLead());
		List<ActionHistoryVO> actionsHistory = getLeadsService().findActionsHistory(lead);		
		return UtilXML.buildXmlGridActionsHistory(actionsHistory);
	}

	@Override
	public void insert(DhtmlxGridForm f) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(DhtmlxGridForm f) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(DhtmlxGridForm f) throws Exception {
		GridActionsHistoryForm form = (GridActionsHistoryForm) f;
		ActionHistoryVO action = new ActionHistoryVO();
		action.setIdActionHistory(form.getGr_id());
		getLeadsService().deleteActionHistory(action);
		LeadVO lead = new LeadVO();
		lead.setIdLead(form.getIdLead());
		getLeadsService().updateFechaModificacion(lead);
	}


}
