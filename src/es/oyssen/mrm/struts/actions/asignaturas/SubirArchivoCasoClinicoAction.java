package es.oyssen.mrm.struts.actions.asignaturas;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import es.oyssen.mrm.negocio.vo.PortafolioVO;
import es.oyssen.mrm.struts.actions.MrmAction;
import es.oyssen.mrm.struts.forms.asignaturas.SubirArchivoCasoClinicoForm;
import es.oyssen.mrm.struts.forms.ficheros.SubirFicheroForm;

public class SubirArchivoCasoClinicoAction extends MrmAction {

	@Override
	public ActionForward process(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SubirArchivoCasoClinicoForm f = (SubirArchivoCasoClinicoForm) form;	
		String nombre = f.getFichero().getFileName();
		String[] sp = nombre.split("\\.");
		if(sp[1].equals("pdf") && f.getFichero().getFileSize()<999999) {

			getCasosClinicosService().process(f);
			return mapping.findForward("success");
		}
		
		return mapping.findForward("error");
	}

}
