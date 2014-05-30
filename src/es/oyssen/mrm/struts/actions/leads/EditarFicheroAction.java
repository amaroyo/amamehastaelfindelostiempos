package es.oyssen.mrm.struts.actions.leads;

import es.oyssen.mrm.negocio.vo.FicheroVO;
import es.oyssen.mrm.struts.actions.dhtmlx.DHTMLXFormAction;
import es.oyssen.mrm.struts.forms.dhtmlx.DhtmlxForm;
import es.oyssen.mrm.struts.forms.ficheros.FicheroForm;
import es.oyssen.mrm.util.StringUtil;

public class EditarFicheroAction extends DHTMLXFormAction {

	@Override
	public Object load(DhtmlxForm f) throws Exception {
		FicheroForm form = (FicheroForm) f;
		FicheroVO fichero = new FicheroVO();
		fichero.setIdFichero(form.getIdFichero());
		return getFicherosLeadService().findById(fichero);
	}

	@Override
	public String save(DhtmlxForm f) throws Exception {
		FicheroForm form = (FicheroForm) f;
		FicheroVO fichero = new FicheroVO();
		fichero.setIdFichero(form.getIdFichero());
		fichero.setIdServicio(form.getIdServicio());
		fichero.setNombre(form.getNombre());
		fichero.setTipoContenido(form.getTipoContenido());
		
		if (!StringUtil.isNullOrBlank(fichero.getIdFichero())) {
			getFicherosLeadService().update(fichero);
		} else {
			getFicherosLeadService().insert(fichero);
		}
		return null;
	}

	@Override
	public String parseXML(Object o) throws Exception {
		FicheroVO c = (FicheroVO) o;
		StringBuffer sb = new StringBuffer();
		sb.append("<data>");
		sb.append("<idFichero><![CDATA[" + c.getIdFichero() + "]]></idFichero>");
		sb.append("<idLead><![CDATA[" + c.getIdLead() + "]]></idLead>");
		sb.append("<nombre><![CDATA[" + c.getNombre() + "]]></nombre>");
		sb.append("<tipoContenido><![CDATA[" + c.getTipoContenido() + "]]></tipoContenido>");
		sb.append("</data>");
		
		return sb.toString();
	}

	@Override
	public String create(DhtmlxForm f) throws Exception {
		// TODO Auto-generated method stub
		return null;
		
	}

}
