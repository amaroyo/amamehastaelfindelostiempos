package es.oyssen.mrm.struts.actions.canales;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import es.oyssen.mrm.negocio.vo.CriterioLeadVO;
import es.oyssen.mrm.negocio.vo.LeadVO;
import es.oyssen.mrm.struts.actions.dhtmlx.DHTMLXGridAction;
import es.oyssen.mrm.struts.actions.dhtmlx.DHTMLXGridWithRequestAction;
import es.oyssen.mrm.struts.forms.canales.GridCanalesForm;
import es.oyssen.mrm.struts.forms.dhtmlx.DhtmlxGridForm;
import es.oyssen.mrm.util.UtilXML;


public class GridLeadsAction extends DHTMLXGridWithRequestAction {
 
	public String search(DhtmlxGridForm f, HttpServletRequest request) throws Exception {
		GridCanalesForm form = (GridCanalesForm) f;		
		CriterioLeadVO criterio = new CriterioLeadVO();
		criterio.setCanal(form.getIdCanal());
		String grupo = (String) request.getSession().getAttribute("usuarioIdGrupo");
		if (grupo.equals("2"))
			criterio.setCanal((String) request.getSession().getAttribute("usuarioIdCanal"));
		else if (grupo.equals("3")) 
			criterio.setDistribuidor((String) request.getSession().getAttribute("usuarioIdDistribuidor"));
		else if (grupo.equals("4")) 
			criterio.setComercial((String) request.getSession().getAttribute("usuarioIdComercial"));		
		List<LeadVO> leads = getLeadsService().findByCriterio(criterio);
		return UtilXML.buildXmlGridLeadsCanales(leads);
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
