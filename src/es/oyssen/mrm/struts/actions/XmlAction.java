package es.oyssen.mrm.struts.actions;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public abstract class XmlAction extends MrmAction {

	public ActionForward process(ActionMapping mapping, 
			ActionForm form,
			HttpServletRequest request, 
			HttpServletResponse response)
			throws Exception {
		
		response.setContentType("text/xml");
		response.setCharacterEncoding("UTF-8");
		ServletOutputStream sos = response.getOutputStream();
		byte[] xml = buildXmlDocument(mapping, form, request, response).getBytes("UTF-8");
		
//		log.debug("XML: " + new String(xml, "utf-8"));
		
		sos.write(xml);
		sos.flush();
		sos.close();

		return null;
	}
	
	public abstract String buildXmlDocument(ActionMapping mapping, 
			ActionForm form,
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception;
}
