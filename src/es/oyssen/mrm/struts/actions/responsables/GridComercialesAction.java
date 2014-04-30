package es.oyssen.mrm.struts.actions.responsables;

import java.util.List;

import es.oyssen.mrm.negocio.vo.ComercialVO;
import es.oyssen.mrm.negocio.vo.ResponsableVO;
import es.oyssen.mrm.struts.actions.dhtmlx.DHTMLXGridAction;
import es.oyssen.mrm.struts.forms.dhtmlx.DhtmlxGridForm;
import es.oyssen.mrm.struts.forms.responsables.GridResponsablesForm;
import es.oyssen.mrm.util.UtilXML;


public class GridComercialesAction extends DHTMLXGridAction {
 
	public String search(DhtmlxGridForm f) throws Exception {
		GridResponsablesForm form = (GridResponsablesForm) f;		
		ResponsableVO responsable = new ResponsableVO();
		responsable.setIdResponsable(form.getIdResponsable());
		List<ComercialVO> comerciales = getResponsablesService().findComerciales(responsable);		
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
