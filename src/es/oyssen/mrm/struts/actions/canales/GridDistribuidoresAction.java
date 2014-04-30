package es.oyssen.mrm.struts.actions.canales;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import es.oyssen.mrm.negocio.vo.CriterioDistribuidorVO;
import es.oyssen.mrm.negocio.vo.DistribuidorVO;
import es.oyssen.mrm.struts.actions.dhtmlx.DHTMLXGridWithRequestAction;
import es.oyssen.mrm.struts.forms.canales.GridCanalesForm;
import es.oyssen.mrm.struts.forms.dhtmlx.DhtmlxGridForm;
import es.oyssen.mrm.util.UtilXML;


public class GridDistribuidoresAction extends DHTMLXGridWithRequestAction {
 
	public String search(DhtmlxGridForm f, HttpServletRequest request) throws Exception {
		GridCanalesForm form = (GridCanalesForm) f;		
		CriterioDistribuidorVO criterio = new CriterioDistribuidorVO();
		criterio.setIdCanal(form.getIdCanal());
		String grupo = (String) request.getSession().getAttribute("usuarioIdGrupo");		
		if (grupo.equals("3") || grupo.equals("4")) 		
			criterio.setIdDistribuidor((String) request.getSession().getAttribute("usuarioIdDistribuidor"));
		List<DistribuidorVO> distribuidores = getDistribuidoresService().findByCriterio(criterio);
		return UtilXML.buildXmlGridDistribuidores(distribuidores);
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
