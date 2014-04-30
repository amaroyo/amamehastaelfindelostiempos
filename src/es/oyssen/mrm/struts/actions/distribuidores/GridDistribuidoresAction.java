package es.oyssen.mrm.struts.actions.distribuidores;

import javax.servlet.http.HttpServletRequest;

import es.oyssen.mrm.negocio.vo.CriterioDistribuidorVO;
import es.oyssen.mrm.negocio.vo.DistribuidorVO;
import es.oyssen.mrm.struts.actions.dhtmlx.DHTMLXGridWithRequestAction;
import es.oyssen.mrm.struts.forms.dhtmlx.DhtmlxGridForm;
import es.oyssen.mrm.util.UtilXML;

public class GridDistribuidoresAction extends DHTMLXGridWithRequestAction {

	@Override
	public String search(DhtmlxGridForm f, HttpServletRequest request) throws Exception {
		CriterioDistribuidorVO criterio = new CriterioDistribuidorVO();		
		String grupo = (String) request.getSession().getAttribute("usuarioIdGrupo");		
		if (grupo.equals("2"))
			criterio.setIdCanal((String) request.getSession().getAttribute("usuarioIdCanal"));
		else if (grupo.equals("3") || grupo.equals("4")) 
			criterio.setIdDistribuidor((String) request.getSession().getAttribute("usuarioIdDistribuidor"));
		return UtilXML.buildXmlGridDistribuidores(getDistribuidoresService().findByCriterio(criterio));
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
		DistribuidorVO distribuidor = new DistribuidorVO();
		distribuidor.setIdDistribuidor(f.getGr_id());
		getDistribuidoresService().delete(distribuidor);		
	}

}
