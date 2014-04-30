package es.oyssen.mrm.struts.forms.leads;

import es.oyssen.mrm.struts.forms.dhtmlx.DhtmlxGridForm;

public class GridActionsHistoryForm extends DhtmlxGridForm {
	private String idActionHistory;
	private String idLead;
	
	public String getIdActionHistory() {
		return idActionHistory;
	}
	public void setIdActionHistory(String idActionHistory) {
		this.idActionHistory = idActionHistory;
	}
	public String getIdLead() {
		return idLead;
	}
	public void setIdLead(String idLead) {
		this.idLead = idLead;
	}
	
}
