package es.oyssen.mrm.struts.actions.contactos.distribuidor;

import es.oyssen.mrm.negocio.vo.ContactoDistribuidorVO;
import es.oyssen.mrm.negocio.vo.CriterioContactoDistribuidorVO;
import es.oyssen.mrm.struts.actions.dhtmlx.DHTMLXGridAction;
import es.oyssen.mrm.struts.forms.contactos.distribuidor.GridContactosDistribuidorForm;
import es.oyssen.mrm.struts.forms.dhtmlx.DhtmlxGridForm;
import es.oyssen.mrm.util.UtilXML;

public class GridContactosDistribuidorAction extends DHTMLXGridAction {

	@Override
	public String search(DhtmlxGridForm f) throws Exception {
		GridContactosDistribuidorForm form = (GridContactosDistribuidorForm) f;
		CriterioContactoDistribuidorVO criterio = new CriterioContactoDistribuidorVO();
		criterio.setIdDistribuidor(form.getIdDistribuidor());
		return UtilXML.buildXmlGridContactoDistribuidor(getContactosDistribuidorService().findByCriterio(criterio));
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
		ContactoDistribuidorVO contacto = new ContactoDistribuidorVO();
		contacto.setIdContacto(f.getGr_id());
		getContactosDistribuidorService().delete(contacto);		
	}

}
