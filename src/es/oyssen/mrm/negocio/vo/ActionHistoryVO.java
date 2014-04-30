package es.oyssen.mrm.negocio.vo;

import java.util.Date;

public class ActionHistoryVO {
	private String idActionHistory;
	private ActionVO action;
	private String comments;
	private Date date;
	
	public void setIdActionHistory(String idActionHistory) {
		this.idActionHistory = idActionHistory;
	}
	public String getIdActionHistory() {
		return idActionHistory;
	}
	public ActionVO getAction() {
		return action;
	}
	public void setAction(ActionVO action) {
		this.action = action;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Date getDate() {
		return date;
	}
}
