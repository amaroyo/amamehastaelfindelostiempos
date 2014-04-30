package es.oyssen.mrm.struts.actions.comerciales;

import javax.servlet.http.HttpServletRequest;

import es.oyssen.mrm.negocio.vo.CriterioComercialVO;
import es.oyssen.mrm.negocio.vo.ComercialVO;
import es.oyssen.mrm.struts.actions.dhtmlx.DHTMLXGridWithRequestAction;
import es.oyssen.mrm.struts.forms.dhtmlx.DhtmlxGridForm;
import es.oyssen.mrm.util.UtilXML;


public class GridComercialesAction extends DHTMLXGridWithRequestAction {
	
	@Override
	public String search(DhtmlxGridForm f, HttpServletRequest request) throws Exception {		
		CriterioComercialVO criterio = new CriterioComercialVO();
		String grupo = (String) request.getSession().getAttribute("usuarioIdGrupo");
		if (grupo.equals("2"))
			criterio.setIdCanal((String) request.getSession().getAttribute("usuarioIdCanal"));
		else if (grupo.equals("3")) 
			criterio.setIdDistribuidor((String) request.getSession().getAttribute("usuarioIdDistribuidor"));
		else if (grupo.equals("4")) 
			criterio.setIdComercial((String) request.getSession().getAttribute("usuarioIdComercial"));
		return UtilXML.buildXmlGridComerciales(getComercialesService().findByCriterio(criterio));
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
		ComercialVO comercial = new ComercialVO();
		comercial.setIdComercial(f.getGr_id());
		getComercialesService().delete(comercial);
	}

}
