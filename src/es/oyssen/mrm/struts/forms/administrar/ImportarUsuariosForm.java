package es.oyssen.mrm.struts.forms.administrar;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

public class ImportarUsuariosForm extends ActionForm {
	private FormFile fichero;
	private String idMarketingActivity;
	private String canal;
	
	public void setIdMarketingActivity(String idMarketingActivity) {
		this.idMarketingActivity = idMarketingActivity;
	}
	public String getIdMarketingActivity() {
		return idMarketingActivity;
	}
	public void setFichero(FormFile fichero) {
		this.fichero = fichero;
	}
	public FormFile getFichero() {
		return fichero;
	}
	public String getCanal() {
		return canal;
	}
	public void setCanal(String canal) {
		this.canal = canal;
	}
}
