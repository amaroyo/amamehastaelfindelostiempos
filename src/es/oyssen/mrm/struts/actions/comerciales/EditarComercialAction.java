package es.oyssen.mrm.struts.actions.comerciales;

import es.oyssen.mrm.negocio.vo.ComercialVO;
import es.oyssen.mrm.struts.actions.dhtmlx.DHTMLXFormAction;
import es.oyssen.mrm.struts.forms.comerciales.ComercialForm;
import es.oyssen.mrm.struts.forms.dhtmlx.DhtmlxForm;
import es.oyssen.mrm.util.StringUtil;

public class EditarComercialAction extends DHTMLXFormAction {

	@Override
	public Object load(DhtmlxForm f) throws Exception {
		ComercialForm form = (ComercialForm) f;
		ComercialVO comercial = new ComercialVO();
		comercial.setIdComercial(form.getIdComercial());
		return getComercialesService().findById(comercial);
	}

	@Override
	public String save(DhtmlxForm f) throws Exception {
		ComercialForm form = (ComercialForm) f;
		ComercialVO comercial = new ComercialVO();
		comercial.setIdComercial(form.getIdComercial());
		comercial.setIdDistribuidor(form.getIdDistribuidor());
		comercial.setNombre(form.getNombre());
		comercial.setTelefono(form.getTelefono());
		comercial.setTelefonoMovil(form.getTelefonoMovil());
		comercial.setDireccion(form.getDireccion());
		comercial.setCodigoPostal(form.getCodigoPostal());
		comercial.setCiudad(form.getCiudad());
		comercial.setPais(form.getPais());
		comercial.setEmail(form.getEmail());
		comercial.setComentarios(form.getComentarios());
		
		if (!StringUtil.isNullOrBlank(comercial.getIdComercial())) {
			getComercialesService().update(comercial);
		} else {
			getComercialesService().insert(comercial);
		}

		return null;
	}

	@Override
	public String parseXML(Object o) throws Exception {
		ComercialVO c = (ComercialVO) o;
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
	public String create(DhtmlxForm f) throws Exception {
		// TODO Auto-generated method stub

		return null;
		
	}

}
