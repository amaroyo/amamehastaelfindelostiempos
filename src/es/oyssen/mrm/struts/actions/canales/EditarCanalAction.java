package es.oyssen.mrm.struts.actions.canales;

import es.oyssen.mrm.negocio.vo.CanalVO;
import es.oyssen.mrm.struts.actions.dhtmlx.DHTMLXFormAction;
import es.oyssen.mrm.struts.forms.canales.CanalForm;
import es.oyssen.mrm.struts.forms.dhtmlx.DhtmlxForm;
import es.oyssen.mrm.util.StringUtil;

public class EditarCanalAction extends DHTMLXFormAction {

	@Override
	public Object load(DhtmlxForm f) throws Exception {
		CanalForm form = (CanalForm) f;
		CanalVO canal = new CanalVO();
		canal.setIdCanal(form.getIdCanal());
		return getCanalesService().findById(canal);
	}

	@Override
	public void save(DhtmlxForm f) throws Exception {
		CanalForm form = (CanalForm) f;
		CanalVO canal = new CanalVO();
		canal.setIdCanal(form.getIdCanal());
		canal.setNombre(form.getNombre());
		canal.setTelefono(form.getTelefono());
		canal.setTelefonoMovil(form.getTelefonoMovil());
		canal.setDireccion(form.getDireccion());
		canal.setCodigoPostal(form.getCodigoPostal());
		canal.setCiudad(form.getCiudad());
		canal.setPais(form.getPais());
		canal.setEmail(form.getEmail());
		canal.setDireccionFacturacion(form.getDireccionFacturacion());
		canal.setCodigoPostalFacturacion(form.getCodigoPostalFacturacion());
		canal.setCiudadFacturacion(form.getCiudadFacturacion());
		canal.setPaisFacturacion(form.getPaisFacturacion());
		canal.setNombreFacturacion(form.getNombreFacturacion());
		canal.setTelefonoFacturacion(form.getTelefonoFacturacion());
		canal.setTelefonoMovilFacturacion(form.getTelefonoMovilFacturacion());
		canal.setEmailFacturacion(form.getEmailFacturacion());
		canal.setDireccionWeb(form.getDireccionWeb());
		canal.setMrmResponsable(form.getMrmResponsable());
		
		if (!StringUtil.isNullOrBlank(canal.getIdCanal())) {
			getCanalesService().update(canal);
		} else {
			getCanalesService().insert(canal);
		}
	}

	@Override
	public String parseXML(Object o) throws Exception {
		CanalVO c = (CanalVO) o;
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
		sb.append("<direccionFacturacion><![CDATA[" + c.getDireccionFacturacion() + "]]></direccionFacturacion>");
		sb.append("<codigoPostalFacturacion><![CDATA[" + c.getCodigoPostalFacturacion() + "]]></codigoPostalFacturacion>");
		sb.append("<ciudadFacturacion><![CDATA[" + c.getCiudadFacturacion() + "]]></ciudadFacturacion>");
		sb.append("<paisFacturacion><![CDATA[" + c.getPaisFacturacion() + "]]></paisFacturacion>");
		sb.append("<nombreFacturacion><![CDATA[" + c.getNombreFacturacion() + "]]></nombreFacturacion>");
		sb.append("<telefonoFacturacion><![CDATA[" + c.getTelefonoFacturacion() + "]]></telefonoFacturacion>");
		sb.append("<telefonoMovilFacturacion><![CDATA[" + c.getTelefonoMovilFacturacion() + "]]></telefonoMovilFacturacion>");
		sb.append("<emailFacturacion><![CDATA[" + c.getEmailFacturacion() + "]]></emailFacturacion>");
		sb.append("<direccionWeb><![CDATA[" + c.getDireccionWeb() + "]]></direccionWeb>");
		sb.append("<mrmResponsable><![CDATA[" + c.getMrmResponsable() + "]]></mrmResponsable>");
		sb.append("</data>");
		
		return sb.toString();
	}

}
