package es.oyssen.mrm.struts.actions.empresas;

import es.oyssen.mrm.negocio.vo.CriterioEmpresaVO;
import es.oyssen.mrm.negocio.vo.EmpresaVO;
import es.oyssen.mrm.struts.actions.dhtmlx.DHTMLXFormAction;
import es.oyssen.mrm.struts.forms.dhtmlx.DhtmlxForm;
import es.oyssen.mrm.struts.forms.empresas.EmpresaForm;

public class CargarEmpresaAction extends DHTMLXFormAction {

	@Override
	public Object load(DhtmlxForm f) throws Exception {
		EmpresaForm form = (EmpresaForm) f;
		EmpresaVO empresa = new EmpresaVO();
		empresa.setOrgN(form.getOrgnr());
		return getEmpresasService().findByOrgn(empresa);
	}

	@Override
	public void save(DhtmlxForm f) throws Exception {
		/*EmpresaForm form = (EmpresaForm) f;
		EmpresaVO empresa = new EmpresaVO();
		empresa.setIdEmpresa(form.getIdEmpresa());
		empresa.setOrgN(form.getOrgnr());
		empresa.setNombre(form.getNombre());		
		empresa.setTelefono(form.getTelefono());
		empresa.setTelefonoMovil(form.getTelefonoMovil());
		empresa.setEmail(form.getEmail());
		empresa.setInformacionContacto(form.getInformacionContacto());
		
		if (!StringUtil.isNullOrBlank(empresa.getIdEmpresa())) {
			getEmpresasService().update(empresa);
		} else{
			getEmpresasService().insert(empresa);
		}*/
	}

	@Override
	public String parseXML(Object o) throws Exception {
			EmpresaVO e = (EmpresaVO) o;
			StringBuffer sb = new StringBuffer();
		if (e != null){
			sb.append("<data>");
			sb.append("<orgnr><![CDATA[" + e.getOrgN() + "]]></orgnr>");
			sb.append("<nombre><![CDATA[" + e.getNombre() + "]]></nombre>");
			sb.append("<telefono><![CDATA[" + e.getTelefono() + "]]></telefono>");
			sb.append("<telefonoMovil><![CDATA[" + e.getTelefonoMovil() + "]]></telefonoMovil>");
			sb.append("<email><![CDATA[" + e.getEmail() + "]]></email>");
			sb.append("<informacionContacto><![CDATA[" + e.getInformacionContacto() + "]]></informacionContacto>");
			sb.append("<direccion><![CDATA[" + e.getDireccion() + "]]></direccion>");
			sb.append("</data>");
		} else {
			sb.append("<data>");
			sb.append("<nombre></nombre>");
			sb.append("<telefono></telefono>");
			sb.append("<telefonoMovil></telefonoMovil>");
			sb.append("<email></email>");
			sb.append("<informacionContacto></informacionContacto>");
			sb.append("</data>");
		} 
		
		
		return sb.toString();
	}

}
