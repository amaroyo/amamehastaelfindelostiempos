package es.oyssen.mrm.struts.actions.leads;

import es.oyssen.mrm.negocio.vo.CriterioFicheroVO;
import es.oyssen.mrm.negocio.vo.FicheroVO;
import es.oyssen.mrm.struts.actions.dhtmlx.DHTMLXGridAction;
import es.oyssen.mrm.struts.forms.dhtmlx.DhtmlxGridForm;
import es.oyssen.mrm.struts.forms.ficheros.GridFicherosForm;
import es.oyssen.mrm.util.UtilXML;

public class GridFicherosAction extends DHTMLXGridAction {

	@Override
	public String search(DhtmlxGridForm f) throws Exception {
		GridFicherosForm form = (GridFicherosForm) f;
		CriterioFicheroVO criterio = new CriterioFicheroVO();
		criterio.setIdLead(form.getIdLead());
		return UtilXML.buildXmlGridFichero(getFicherosLeadService().findByCriterio(criterio));
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
		FicheroVO fichero = new FicheroVO();
		fichero.setIdFichero(f.getGr_id());
		getFicherosLeadService().delete(fichero);		
	}

}
