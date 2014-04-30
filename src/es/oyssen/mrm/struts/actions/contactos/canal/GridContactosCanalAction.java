package es.oyssen.mrm.struts.actions.contactos.canal;

import es.oyssen.mrm.negocio.vo.ContactoCanalVO;
import es.oyssen.mrm.negocio.vo.CriterioContactoCanalVO;
import es.oyssen.mrm.struts.actions.dhtmlx.DHTMLXGridAction;
import es.oyssen.mrm.struts.forms.contactos.canal.GridContactosCanalForm;
import es.oyssen.mrm.struts.forms.dhtmlx.DhtmlxGridForm;
import es.oyssen.mrm.util.UtilXML;

public class GridContactosCanalAction extends DHTMLXGridAction {

	@Override
	public String search(DhtmlxGridForm f) throws Exception {
		GridContactosCanalForm form = (GridContactosCanalForm) f;
		CriterioContactoCanalVO criterio = new CriterioContactoCanalVO();
		criterio.setIdCanal(form.getIdCanal());
		return UtilXML.buildXmlGridContactoCanal(getContactosCanalService().findByCriterio(criterio));
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
		ContactoCanalVO contacto = new ContactoCanalVO();
		contacto.setIdContacto(f.getGr_id());
		getContactosCanalService().delete(contacto);		
	}

}
