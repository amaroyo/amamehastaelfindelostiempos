package es.oyssen.mrm.struts.actions.administrar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import es.oyssen.mrm.struts.actions.MrmAction;
import es.oyssen.mrm.struts.forms.ficheros.SubirFicheroForm;

public class ImportarAlumnosAction extends MrmAction {

	@Override
	public ActionForward process(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		/*SubirFicheroForm f = (SubirFicheroForm) form;
		System.out.println(form.toString());
		getFicherosLeadService().process(f);*/
		
		return mapping.findForward("success");
	}

}