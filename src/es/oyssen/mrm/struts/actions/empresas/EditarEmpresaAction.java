package es.oyssen.mrm.struts.actions.empresas;

import es.oyssen.mrm.negocio.vo.EmpresaVO;
import es.oyssen.mrm.struts.actions.dhtmlx.DHTMLXFormAction;
import es.oyssen.mrm.struts.forms.dhtmlx.DhtmlxForm;
import es.oyssen.mrm.struts.forms.empresas.EmpresaForm;
import es.oyssen.mrm.util.StringUtil;

public class EditarEmpresaAction extends DHTMLXFormAction {

	@Override
	public Object load(DhtmlxForm f) throws Exception {
		EmpresaForm form = (EmpresaForm) f;
		EmpresaVO empresa = new EmpresaVO();
		empresa.setIdEmpresa(form.getIdEmpresa());
		return getEmpresasService().findById(empresa);
	}

	@Override
	public String save(DhtmlxForm f) throws Exception {
		EmpresaForm form = (EmpresaForm) f;
		EmpresaVO empresa = new EmpresaVO();
		empresa.setIdEmpresa(form.getIdEmpresa());
		empresa.setOrgN(form.getOrgnr());
		empresa.setNombre(form.getNombre());		
		empresa.setTelefono(form.getTelefono());
		empresa.setTelefonoMovil(form.getTelefonoMovil());
		empresa.setEmail(form.getEmail());
		empresa.setInformacionContacto(form.getInformacionContacto());
		empresa.setDireccion(form.getDireccion());
		
		if (!StringUtil.isNullOrBlank(empresa.getIdEmpresa())) {
			getEmpresasService().update(empresa);
		} else{
			getEmpresasService().insert(empresa);
		}

		return null;
	}

	@Override
	public String parseXML(Object o) throws Exception {
		EmpresaVO e = (EmpresaVO) o;
		StringBuffer sb = new StringBuffer();
		sb.append("<data>");
		sb.append("<orgnr><![CDATA[" + e.getOrgN() + "]]></orgnr>");
		sb.append("<nombre><![CDATA[" + e.getNombre() + "]]></nombre>");
		sb.append("<telefono><![CDATA[" + e.getTelefono() + "]]></telefono>");
		sb.append("<telefonoMovil><![CDATA[" + e.getTelefonoMovil() + "]]></telefonoMovil>");
		sb.append("<email><![CDATA[" + e.getEmail() + "]]></email>");
		sb.append("<informacionContacto><![CDATA[" + e.getInformacionContacto() + "]]></informacionContacto>");
		sb.append("<direccion><![CDATA[" + e.getDireccion() + "]]></direccion>");
		sb.append("</data>");
		
		return sb.toString();
	}

	@Override
	public String create(DhtmlxForm f) throws Exception {
		// TODO Auto-generated method stub

		return null;
	}

}
