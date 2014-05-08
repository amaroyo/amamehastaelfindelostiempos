package es.oyssen.mrm.struts.actions.responsables;

import es.oyssen.mrm.negocio.vo.ResponsableVO;
import es.oyssen.mrm.struts.actions.dhtmlx.DHTMLXFormAction;
import es.oyssen.mrm.struts.forms.dhtmlx.DhtmlxForm;
import es.oyssen.mrm.struts.forms.responsables.ResponsableForm;
import es.oyssen.mrm.util.StringUtil;

public class EditarResponsableAction extends DHTMLXFormAction {

	@Override
	public Object load(DhtmlxForm f) throws Exception {
		ResponsableForm form = (ResponsableForm) f;
		ResponsableVO responsable = new ResponsableVO();
		responsable.setIdResponsable(form.getIdResponsable());
		return getResponsablesService().findById(responsable);
	}

	@Override
	public void save(DhtmlxForm f) throws Exception {
		ResponsableForm form = (ResponsableForm) f;
		ResponsableVO responsable = new ResponsableVO();
		responsable.setIdResponsable(form.getIdResponsable());
		responsable.setNombre(form.getNombre());
		responsable.setTelefono(form.getTelefono());
		responsable.setTelefonoMovil(form.getTelefonoMovil());
		responsable.setDireccion(form.getDireccion());
		responsable.setCodigoPostal(form.getCodigoPostal());
		responsable.setCiudad(form.getCiudad());
		responsable.setPais(form.getPais());
		responsable.setEmail(form.getEmail());
		responsable.setComentarios(form.getComentarios());
		
		if (!StringUtil.isNullOrBlank(responsable.getIdResponsable())) {
			getResponsablesService().update(responsable);
		} else {
			getResponsablesService().insert(responsable);
		}
	}

	@Override
	public String parseXML(Object o) throws Exception {
		ResponsableVO c = (ResponsableVO) o;
		StringBuffer sb = new StringBuffer();
		sb.append("<data>");
		sb.append("<nombre><![CDATA[" + c.getNombre() + "]]></nombre>");
		sb.append("<telefono><![CDATA[" + c.getTelefono() + "]]></telefono>");
		sb.append("<telefonoMovil><![CDATA[" + c.getTelefonoMovil() + "]]></telefonoMovil>");
		sb.append("<direccion><![CDATA[" + c.getDireccion() + "]]></direccion>");
		sb.append("<codigoPostal><![CDATA[" + c.getCodigoPostal() + "]]></codigoPostal>");
		sb.append("<ciudad><![CDATA[" + c.getCiudad() + "]]></ciudad>");
		sb.append("<pais><![CDATA[" + c.getPais() + "]]></pais>");
		sb.append("<email><![CDATA[" + c.getEmail() + "]]></email>");
		sb.append("<comentarios><![CDATA[" + c.getComentarios() + "]]></comentarios>");
		sb.append("</data>");
		
		return sb.toString();
	}

	@Override
	public void create(DhtmlxForm f) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
