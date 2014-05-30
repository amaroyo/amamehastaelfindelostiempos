package es.oyssen.mrm.struts.actions.empresas;

import javax.servlet.http.HttpServletRequest;

import es.oyssen.mrm.negocio.vo.CriterioEmpresaVO;
import es.oyssen.mrm.negocio.vo.EmpresaVO;
import es.oyssen.mrm.struts.actions.dhtmlx.DHTMLXGridWithRequestAction;
import es.oyssen.mrm.struts.forms.dhtmlx.DhtmlxGridForm;
import es.oyssen.mrm.util.UtilXML;

public class GridEmpresasAction extends DHTMLXGridWithRequestAction {

	@Override
	public String search(DhtmlxGridForm f, HttpServletRequest request) throws Exception {
		CriterioEmpresaVO criterio = new CriterioEmpresaVO();
		
		String grupo = (String) request.getSession().getAttribute("usuarioIdGrupo");
		String usuario = (String) request.getSession().getAttribute("idUsuario");
		if (grupo.equals("2"))
			criterio.setCanal((String) request.getSession().getAttribute("usuarioIdCanal"));
		else if (grupo.equals("3")) 
			criterio.setDistribuidor((String) request.getSession().getAttribute("usuarioIdDistribuidor"));
		else if (grupo.equals("4")) 
			criterio.setComercial((String) request.getSession().getAttribute("usuarioIdComercial"));
//		else if (grupo.equals("5"))
//			criterio.setSupplier(usuario);
		
		return UtilXML.buildXmlGridEmpresas(getEmpresasService().findByCriterio(criterio));
	}

	@Override
	public void insert(DhtmlxGridForm f) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(DhtmlxGridForm f) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(DhtmlxGridForm f) throws Exception {
		EmpresaVO empresa = new EmpresaVO();
		empresa.setIdEmpresa(f.getGr_id());
		getEmpresasService().delete(empresa);		
	}

}
