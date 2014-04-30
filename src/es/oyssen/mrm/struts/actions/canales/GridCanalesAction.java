package es.oyssen.mrm.struts.actions.canales;

import javax.servlet.http.HttpServletRequest;

import es.oyssen.mrm.negocio.vo.CanalVO;
import es.oyssen.mrm.negocio.vo.CriterioCanalVO;
import es.oyssen.mrm.struts.actions.dhtmlx.DHTMLXGridWithRequestAction;
import es.oyssen.mrm.struts.forms.dhtmlx.DhtmlxGridForm;
import es.oyssen.mrm.util.UtilXML;

public class GridCanalesAction extends DHTMLXGridWithRequestAction {

	@Override
	public String search(DhtmlxGridForm f, HttpServletRequest request) throws Exception {
		CriterioCanalVO criterio = new CriterioCanalVO();
		String grupo = (String) request.getSession().getAttribute("usuarioIdGrupo");
		if (!grupo.equals("1")) 
			criterio.setIdCanal((String) request.getSession().getAttribute("usuarioIdCanal"));
		return UtilXML.buildXmlGridCanales(getCanalesService().findByCriterio(criterio));
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
		CanalVO canal = new CanalVO();
		canal.setIdCanal(f.getGr_id());
		getCanalesService().delete(canal);		
	}

}
