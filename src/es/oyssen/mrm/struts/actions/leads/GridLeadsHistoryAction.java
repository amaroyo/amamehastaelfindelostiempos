package es.oyssen.mrm.struts.actions.leads;

import java.util.List;

import es.oyssen.mrm.negocio.vo.LeadHistoryVO;
import es.oyssen.mrm.negocio.vo.LeadVO;
import es.oyssen.mrm.struts.actions.dhtmlx.DHTMLXGridAction;
import es.oyssen.mrm.struts.forms.dhtmlx.DhtmlxGridForm;
import es.oyssen.mrm.struts.forms.leads.GridLeadsForm;
import es.oyssen.mrm.util.UtilXML;

public class GridLeadsHistoryAction extends DHTMLXGridAction {

	@Override
	public String search(DhtmlxGridForm f) throws Exception {
		GridLeadsForm form = (GridLeadsForm) f;
		LeadVO lead = new LeadVO();
		lead.setIdLead(form.getIdLead());
		List<LeadHistoryVO> leadsHistory = getLeadsHistoryService().findByLead(lead);
		return UtilXML.buildXmlGridLeadsHistory(leadsHistory);
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
		// TODO Auto-generated method stub		
	}


}
