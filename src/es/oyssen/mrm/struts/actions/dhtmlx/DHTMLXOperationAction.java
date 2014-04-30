package es.oyssen.mrm.struts.actions.dhtmlx;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import es.oyssen.mrm.struts.actions.MrmAction;

public abstract class DHTMLXOperationAction extends MrmAction {

	public ActionForward process(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		response.setContentType("text/xml");
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
		try {
			processRequest(mapping, form, request, response);
			sb.append("<data><action type='ok'><![CDATA[Operacion realizada correctamente]]></action></data>");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error procesando la peticion", e);
			sb.append("<action type='error'><![CDATA[No se ha podido realizar la operacion]]></action>");
		}
		
		response.getOutputStream().write(sb.toString().getBytes("utf-8"));
		
		return null;
	}

	
	public abstract void processRequest (ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception;	
}
