package es.oyssen.mrm.struts.actions.comerciales;

import java.util.List;

import es.oyssen.mrm.negocio.vo.ComercialVO;
import es.oyssen.mrm.negocio.vo.CriterioLeadVO;
import es.oyssen.mrm.negocio.vo.LeadVO;
import es.oyssen.mrm.struts.actions.dhtmlx.DHTMLXGridAction;
import es.oyssen.mrm.struts.forms.comerciales.GridComercialesForm;
import es.oyssen.mrm.struts.forms.dhtmlx.DhtmlxGridForm;
import es.oyssen.mrm.util.UtilXML;


public class GridLeadsAction extends DHTMLXGridAction {
 
	public String search(DhtmlxGridForm f) throws Exception {	
		GridComercialesForm form = (GridComercialesForm) f;		
		CriterioLeadVO criterio = new CriterioLeadVO();
		criterio.setComercial(form.getIdComercial());
		List<LeadVO> leads = getLeadsService().findByCriterio(criterio);
		return UtilXML.buildXmlGridLeadsComerciales(leads);
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
