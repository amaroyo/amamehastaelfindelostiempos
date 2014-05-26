package es.oyssen.mrm.struts.actions.dhtmlx;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

import es.oyssen.mrm.struts.actions.MrmAction;
import es.oyssen.mrm.struts.forms.dhtmlx.DhtmlxGridForm;
import es.oyssen.mrm.util.StringUtil;

public abstract class DHTMLXGridAction extends MrmAction {
	
	public static final String RESULTADO_ERROR = "error";
	public String anyoAcademico;

	public ActionForward process(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		anyoAcademico = (String) request.getSession().getAttribute("anyoAcademico");
		MessageResources resources = getResources(request);
		DhtmlxGridForm f = (DhtmlxGridForm) form;
		f.setNativeeditor_status(request.getParameter("!nativeeditor_status"));
		
		if (!StringUtil.isNullOrBlank(f.getNativeeditor_status())) {
			StringBuffer xml = new StringBuffer();
			xml.append("<data>");
			try {
				f.setOldId(f.getGr_id());
				f.setNewId(f.getGr_id());
				
				if (f.getNativeeditor_status().equals("inserted")) {
					f.setOperation("insert");
					insert(f);					
				} else if (f.getNativeeditor_status().equals("updated")) {
					f.setOperation("update");	
					update(f);					
				} else if (f.getNativeeditor_status().equals("deleted")) {
					f.setOperation("delete");
					delete(f);					
				}		
				xml.append("<action type='" + f.getOperation() + "' sid='" + f.getOldId() + "' tid='" +f.getNewId() + "'><![CDATA[" + f.getContenido() + "]]></action>");
			
			} catch (Exception e) {
				e.printStackTrace();
				xml.append("<action type='" + RESULTADO_ERROR + "'><![CDATA[" + resources.getMessage("error.operacion.ko") + "]]></action>");
			}
			xml.append("</data>");
			writeGridXML(response, xml.toString());
		} else {
			writeGridXML(response, search(f));
		}
		
		return mapping.findForward("success");
	}

	protected void writeGridXML(HttpServletResponse response,
			String xmlGrid) throws IOException,
			UnsupportedEncodingException {
		response.setContentType("text/xml");
		ServletOutputStream sos = response.getOutputStream();
		sos.write(xmlGrid.getBytes("UTF-8"));
		sos.flush();
		sos.close();
	}

	public abstract String search(DhtmlxGridForm f) throws Exception;
	
	public abstract void insert(DhtmlxGridForm f) throws Exception;
	
	public abstract void update(DhtmlxGridForm f) throws Exception;
	
	public abstract void delete(DhtmlxGridForm f) throws Exception; 
}
