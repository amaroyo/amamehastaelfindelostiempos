package es.oyssen.mrm.struts.actions.servicios;

import es.oyssen.mrm.negocio.vo.ServicioVO;
import es.oyssen.mrm.struts.actions.dhtmlx.DHTMLXFormAction;
import es.oyssen.mrm.struts.forms.dhtmlx.DhtmlxForm;
import es.oyssen.mrm.struts.forms.servicios.ServicioForm;
import es.oyssen.mrm.util.StringUtil;

public class EditarServicioAction extends DHTMLXFormAction {

	@Override
	public Object load(DhtmlxForm f) throws Exception {
		ServicioForm form = (ServicioForm) f;
		ServicioVO servicio = new ServicioVO();
		servicio.setIdServicio(form.getIdServicio());
		return getServiciosService().findById(servicio);
	}

	@Override
	public void save(DhtmlxForm f) throws Exception {
		ServicioForm form = (ServicioForm) f;
		ServicioVO servicio = new ServicioVO();
		servicio.setIdServicio(form.getIdServicio());
		servicio.setNombre(form.getNombre());
		servicio.setPersonaContacto(form.getPersonaContacto());
		servicio.setProveedor(form.getProveedor());
		
		if (!StringUtil.isNullOrBlank(servicio.getIdServicio())) {
			getServiciosService().update(servicio);
		} else {
			getServiciosService().insert(servicio);
		}
	}

	@Override
	public String parseXML(Object o) throws Exception {
		ServicioVO c = (ServicioVO) o;
		StringBuffer sb = new StringBuffer();
		sb.append("<data>");
		sb.append("<nombre><![CDATA[" + c.getNombre() + "]]></nombre>");
		sb.append("<personaContacto><![CDATA[" + c.getPersonaContacto() + "]]></personaContacto>");
		sb.append("<proveedor><![CDATA[" + c.getProveedor() + "]]></proveedor>");
		sb.append("</data>");
		
		return sb.toString();
	}

	@Override
	public void create(DhtmlxForm f) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
