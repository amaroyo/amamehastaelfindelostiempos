package es.oyssen.mrm.struts.actions.servicios;

import javax.servlet.http.HttpServletRequest;

import es.oyssen.mrm.negocio.vo.CriterioServicioVO;
import es.oyssen.mrm.negocio.vo.ServicioVO;
import es.oyssen.mrm.struts.actions.dhtmlx.DHTMLXGridWithRequestAction;
import es.oyssen.mrm.struts.forms.dhtmlx.DhtmlxGridForm;
import es.oyssen.mrm.util.UtilXML;

public class GridServiciosAction extends DHTMLXGridWithRequestAction {

	@Override
	public String search(DhtmlxGridForm f, HttpServletRequest request) throws Exception {
		CriterioServicioVO criterio = new CriterioServicioVO();
		String usuario = (String) request.getSession().getAttribute("idUsuario");
		String grupo = (String) request.getSession().getAttribute("usuarioIdGrupo");
		if (!grupo.equals("1"))
			criterio.setIdUsuario(usuario);
		return UtilXML.buildXmlGridServicios(getServiciosService().findByCriterio(criterio));
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
		ServicioVO servicio = new ServicioVO();
		servicio.setIdServicio(f.getGr_id());
		getServiciosService().delete(servicio);		
	}

}
