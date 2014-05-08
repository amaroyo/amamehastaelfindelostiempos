package es.oyssen.mrm.struts.actions.contactos.distribuidor;

import es.oyssen.mrm.negocio.vo.ContactoDistribuidorVO;
import es.oyssen.mrm.struts.actions.dhtmlx.DHTMLXFormAction;
import es.oyssen.mrm.struts.forms.contactos.distribuidor.ContactoDistribuidorForm;
import es.oyssen.mrm.struts.forms.dhtmlx.DhtmlxForm;
import es.oyssen.mrm.util.StringUtil;

public class EditarContactoDistribuidorAction extends DHTMLXFormAction {

	@Override
	public Object load(DhtmlxForm f) throws Exception {
		ContactoDistribuidorForm form = (ContactoDistribuidorForm) f;
		ContactoDistribuidorVO contacto = new ContactoDistribuidorVO();
		contacto.setIdContacto(form.getIdContacto());
		return getContactosDistribuidorService().findById(contacto);
	}

	@Override
	public void save(DhtmlxForm f) throws Exception {
		ContactoDistribuidorForm form = (ContactoDistribuidorForm) f;
		ContactoDistribuidorVO contacto = new ContactoDistribuidorVO();
		contacto.setIdContacto(form.getIdContacto());
		contacto.setIdDistribuidor(form.getIdDistribuidor());
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
			getContactosDistribuidorService().update(contacto);
		} else {
			getContactosDistribuidorService().insert(contacto);
		}
	}

	@Override
	public String parseXML(Object o) throws Exception {
		ContactoDistribuidorVO c = (ContactoDistribuidorVO) o;
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
	public void create(DhtmlxForm f) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
