package es.oyssen.mrm.struts.actions.distribuidores;

import es.oyssen.mrm.negocio.vo.DistribuidorVO;
import es.oyssen.mrm.struts.actions.dhtmlx.DHTMLXFormAction;
import es.oyssen.mrm.struts.forms.dhtmlx.DhtmlxForm;
import es.oyssen.mrm.struts.forms.distribuidores.DistribuidorForm;
import es.oyssen.mrm.util.StringUtil;

public class EditarDistribuidorAction extends DHTMLXFormAction {

	@Override
	public Object load(DhtmlxForm f) throws Exception {
		DistribuidorForm form = (DistribuidorForm) f;
		DistribuidorVO distribuidor = new DistribuidorVO();
		distribuidor.setIdDistribuidor(form.getIdDistribuidor());
		return getDistribuidoresService().findById(distribuidor);
	}

	@Override
	public void save(DhtmlxForm f) throws Exception {
		DistribuidorForm form = (DistribuidorForm) f;
		DistribuidorVO distribuidor = new DistribuidorVO();
		distribuidor.setIdDistribuidor(form.getIdDistribuidor());
		distribuidor.setIdCanal(form.getIdCanal());
		distribuidor.setNombre(form.getNombre());
		distribuidor.setTelefono(form.getTelefono());
		distribuidor.setTelefonoMovil(form.getTelefonoMovil());
		distribuidor.setDireccion(form.getDireccion());
		distribuidor.setCodigoPostal(form.getCodigoPostal());
		distribuidor.setCiudad(form.getCiudad());
		distribuidor.setPais(form.getPais());
		distribuidor.setEmail(form.getEmail());
		distribuidor.setDireccionFacturacion(form.getDireccionFacturacion());
		distribuidor.setCodigoPostalFacturacion(form.getCodigoPostalFacturacion());
		distribuidor.setCiudadFacturacion(form.getCiudadFacturacion());
		distribuidor.setPaisFacturacion(form.getPaisFacturacion());
		distribuidor.setNombreFacturacion(form.getNombreFacturacion());
		distribuidor.setTelefonoFacturacion(form.getTelefonoFacturacion());
		distribuidor.setTelefonoMovilFacturacion(form.getTelefonoMovilFacturacion());
		distribuidor.setEmailFacturacion(form.getEmailFacturacion());
		
		if (!StringUtil.isNullOrBlank(distribuidor.getIdDistribuidor())) {
			getDistribuidoresService().update(distribuidor);
		} else {
			getDistribuidoresService().insert(distribuidor);
		}
	}

	@Override
	public String parseXML(Object o) throws Exception {
		DistribuidorVO c = (DistribuidorVO) o;
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
		sb.append("</data>");
		
		return sb.toString();
	}

	
	public void bloquear(DhtmlxForm f) throws Exception {
		DistribuidorForm form = (DistribuidorForm) f;
		DistribuidorVO distribuidor = new DistribuidorVO();
		distribuidor.setIdDistribuidor(form.getIdDistribuidor());		
		getDistribuidoresService().bloquear(distribuidor);
	}
	
	public void desbloquear(DhtmlxForm f) throws Exception {
		DistribuidorForm form = (DistribuidorForm) f;
		DistribuidorVO distribuidor = new DistribuidorVO();
		distribuidor.setIdDistribuidor(form.getIdDistribuidor());		
		getDistribuidoresService().desbloquear(distribuidor);
	}
	
}
