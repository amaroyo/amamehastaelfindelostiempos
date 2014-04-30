package es.oyssen.mrm.struts.forms.leads;

import es.oyssen.mrm.struts.forms.dhtmlx.DhtmlxForm;

public class ActionsHistoryForm extends DhtmlxForm {
	private String idLead;
	private String action;
	private String comments;
	public String getIdLead() {
		return idLead;
	}
	public void setIdLead(String idLead) {
		this.idLead = idLead;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	

}
