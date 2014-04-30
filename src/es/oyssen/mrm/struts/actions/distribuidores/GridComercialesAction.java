package es.oyssen.mrm.struts.actions.distribuidores;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import es.oyssen.mrm.negocio.vo.ComercialVO;
import es.oyssen.mrm.negocio.vo.CriterioComercialVO;
import es.oyssen.mrm.struts.actions.dhtmlx.DHTMLXGridWithRequestAction;
import es.oyssen.mrm.struts.forms.dhtmlx.DhtmlxGridForm;
import es.oyssen.mrm.struts.forms.distribuidores.GridDistribuidoresForm;
import es.oyssen.mrm.util.UtilXML;


public class GridComercialesAction extends DHTMLXGridWithRequestAction {
 
	public String search(DhtmlxGridForm f, HttpServletRequest request) throws Exception {		
		GridDistribuidoresForm form = (GridDistribuidoresForm) f;				
		CriterioComercialVO criterio = new CriterioComercialVO();
		criterio.setIdDistribuidor(form.getIdDistribuidor());
		String grupo = (String) request.getSession().getAttribute("usuarioIdGrupo");
		if (grupo.equals("4")) 		
			criterio.setIdComercial((String) request.getSession().getAttribute("usuarioIdComercial"));			
		List<ComercialVO> comerciales = getComercialesService().findByCriterio(criterio);		
		return UtilXML.buildXmlGridComerciales(comerciales);
	}


	public void insert(DhtmlxGridForm f) throws Exception {

	}


	public void update(DhtmlxGridForm f) throws Exception {

	}



	@Override
	public void delete(DhtmlxGridForm f) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
