package es.oyssen.mrm.struts.actions.dhtmlx;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

import es.oyssen.mrm.struts.actions.MrmAction;
import es.oyssen.mrm.struts.forms.dhtmlx.DhtmlxForm;
import es.oyssen.mrm.struts.forms.dhtmlx.DhtmlxTreeGridForm;
import es.oyssen.mrm.util.StringUtil;

public abstract class DHTMLXTreeGridAction extends MrmAction {
	
	public static final String RESULTADO_ERROR = "error";

	public ActionForward process(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		MessageResources resources = getResources(request);
		DhtmlxTreeGridForm f = (DhtmlxTreeGridForm) form;
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
				} else if (f.getNativeeditor_status().equals("cuted")) {
					f.setOperation("cut");
					cut(f);
				} else if (f.getNativeeditor_status().equals("copied")) {
					f.setOperation("copy");
					copy(f);					
				}		
				xml.append("<action type='" + f.getOperation() + "' sid='" + f.getOldId() + "' tid='" +f.getNewId() + "'><![CDATA[" + f.getContenido() + "]]></action>");
			
			} catch (Exception e) {
				e.printStackTrace();
				xml.append("<action type='" + RESULTADO_ERROR + "'><![CDATA[" + resources.getMessage("error.operacion.ko") + "]]></action>");
			}
			xml.append("</data>");
			writeGridXML(response, xml.toString());
		} else {
			List lista = search(f);		
			writeGridXML(response, parseListToXML(lista));
		}
		
		return null;
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

	public abstract List search(DhtmlxForm f) throws Exception;

	public abstract String parseListToXML(List lista) throws Exception;
	
	public abstract void insert(DhtmlxForm f) throws Exception;
	
	public abstract void update(DhtmlxForm f) throws Exception;
	
	public abstract void delete(DhtmlxForm f) throws Exception;
	
	public abstract void cut(DhtmlxForm f) throws Exception;
	
	public abstract void copy(DhtmlxForm f) throws Exception;
}
