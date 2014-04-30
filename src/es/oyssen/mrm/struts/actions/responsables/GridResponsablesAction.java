package es.oyssen.mrm.struts.actions.responsables;

import es.oyssen.mrm.negocio.vo.CriterioResponsableVO;
import es.oyssen.mrm.negocio.vo.ResponsableVO;
import es.oyssen.mrm.struts.actions.dhtmlx.DHTMLXGridAction;
import es.oyssen.mrm.struts.forms.dhtmlx.DhtmlxGridForm;
import es.oyssen.mrm.util.UtilXML;

public class GridResponsablesAction extends DHTMLXGridAction {

	@Override
	public String search(DhtmlxGridForm f) throws Exception {
		CriterioResponsableVO criterio = new CriterioResponsableVO();
		return UtilXML.buildXmlGridResponsables(getResponsablesService().findByCriterio(criterio));
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
		ResponsableVO responsable = new ResponsableVO();
		responsable.setIdResponsable(f.getGr_id());
		getResponsablesService().delete(responsable);		
	}

}
