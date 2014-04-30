package es.oyssen.mrm.struts.actions.leads;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import es.oyssen.mrm.negocio.vo.CriterioLeadVO;
import es.oyssen.mrm.negocio.vo.LeadVO;
import es.oyssen.mrm.struts.actions.dhtmlx.DHTMLXGridWithRequestAction;
import es.oyssen.mrm.struts.forms.dhtmlx.DhtmlxGridForm;
import es.oyssen.mrm.struts.forms.leads.GridLeadsForm;
import es.oyssen.mrm.util.UtilXML;

public class GridLeadsAction extends DHTMLXGridWithRequestAction {

	@Override
	public String search(DhtmlxGridForm f, HttpServletRequest request) throws Exception {
		GridLeadsForm form = (GridLeadsForm) f;
		CriterioLeadVO criterio = new CriterioLeadVO();
		criterio.setFiltrarEstadoWon(form.getFiltrarEstadoWon());
		criterio.setFiltrarEstadoLost(form.getFiltrarEstadoLost());
		criterio.setCreacionDesde(form.getCreacionDesde());
		criterio.setCreacionHasta(form.getCreacionHasta());
		criterio.setModificacionDesde(form.getModificacionDesde());
		criterio.setModificacionHasta(form.getModificacionHasta());
		
		request.getSession().setAttribute("filtrarEstadoWon", form.getFiltrarEstadoWon());
		request.getSession().setAttribute("filtrarEstadoLost", form.getFiltrarEstadoLost());
		request.getSession().setAttribute("creacionDesde", form.getCreacionDesde());
		request.getSession().setAttribute("creacionHasta", form.getCreacionHasta());
		request.getSession().setAttribute("modificacionDesde", form.getModificacionDesde());
		request.getSession().setAttribute("modificacionHasta", form.getModificacionHasta());
					
		String grupo = (String) request.getSession().getAttribute("usuarioIdGrupo");
		String usuario = (String) request.getSession().getAttribute("idUsuario");
		if (grupo.equals("2"))
			criterio.setCanal((String) request.getSession().getAttribute("usuarioIdCanal"));
		else if (grupo.equals("3")) 
			criterio.setDistribuidor((String) request.getSession().getAttribute("usuarioIdDistribuidor"));
		else if (grupo.equals("4")) 
			criterio.setComercial((String) request.getSession().getAttribute("usuarioIdComercial"));
		else if (grupo.equals("5"))
			criterio.setSupplier(usuario);
		List<LeadVO> leads = getLeadsService().findByCriterio(criterio);
		return UtilXML.buildXmlGridLeads(leads);
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
		LeadVO lead = new LeadVO();
		lead.setIdLead(f.getGr_id());
		getLeadsService().delete(lead);		
	}


}
