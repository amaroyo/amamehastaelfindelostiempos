package es.oyssen.mrm.struts.actions.contactos.canal;

import es.oyssen.mrm.negocio.vo.ContactoCanalVO;
import es.oyssen.mrm.struts.actions.dhtmlx.DHTMLXFormAction;
import es.oyssen.mrm.struts.forms.contactos.canal.ContactoCanalForm;
import es.oyssen.mrm.struts.forms.dhtmlx.DhtmlxForm;
import es.oyssen.mrm.util.StringUtil;

public class EditarContactoCanalAction extends DHTMLXFormAction {

	@Override
	public Object load(DhtmlxForm f) throws Exception {
		ContactoCanalForm form = (ContactoCanalForm) f;
		ContactoCanalVO contacto = new ContactoCanalVO();
		contacto.setIdContacto(form.getIdContacto());
		return getContactosCanalService().findById(contacto);
	}

	@Override
	public String save(DhtmlxForm f) throws Exception {
		ContactoCanalForm form = (ContactoCanalForm) f;
		ContactoCanalVO contacto = new ContactoCanalVO();
		contacto.setIdContacto(form.getIdContacto());
		contacto.setIdCanal(form.getIdCanal());
		contacto.setNombre(form.getNombre());
		contacto.setTelefono(form.getTelefono());
		contacto.setTelefonoMovil(form.getTelefonoMovil());
		contacto.setDireccion(form.getDireccion());
		contacto.setCodigoPostal(form.getCodigoPostal());
		contacto.setCiudad(form.getCiudad());
		contacto.setPais(form.getPais());
		contacto.setEmail(form.getEmail());
		contacto.setCargo(form.getCargo());
		contacto.setComentarios(form.getComentarios());
		
		if (!StringUtil.isNullOrBlank(contacto.getIdContacto())) {
			getContactosCanalService().update(contacto);
		} else {
			getContactosCanalService().insert(contacto);
		}

		return null;
	}

	@Override
	public String parseXML(Object o) throws Exception {
		ContactoCanalVO c = (ContactoCanalVO) o;
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
		sb.append("<cargo><![CDATA[" + c.getCargo() + "]]></cargo>");
		sb.append("<comentarios><![CDATA[" + c.getComentarios() + "]]></comentarios>");
		sb.append("</data>");
		
		return sb.toString();
	}

	@Override
	public String create(DhtmlxForm f) throws Exception {
		// TODO Auto-generated method stub

		return null;
	}

}
