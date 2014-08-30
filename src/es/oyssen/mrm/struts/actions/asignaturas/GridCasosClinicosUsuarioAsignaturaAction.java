package es.oyssen.mrm.struts.actions.asignaturas;

import es.oyssen.mrm.negocio.vo.PortafolioVO;
import es.oyssen.mrm.struts.actions.dhtmlx.DHTMLXGridAction;
import es.oyssen.mrm.struts.forms.asignaturas.GridCasosClinicosUsuarioAsignaturaForm;
import es.oyssen.mrm.struts.forms.dhtmlx.DhtmlxGridForm;
import es.oyssen.mrm.util.UtilXML;


public class GridCasosClinicosUsuarioAsignaturaAction extends DHTMLXGridAction {
	
	@Override
	public String search(DhtmlxGridForm f) throws Exception {
		
		
		GridCasosClinicosUsuarioAsignaturaForm form = (GridCasosClinicosUsuarioAsignaturaForm) f;
		
		
	
			
			PortafolioVO p = new PortafolioVO();	
			p.setAnyoAcademico(anyoAcademico);
			p.setIdAsignatura(form.getIdAsignatura());
			p.setIdAlumno(form.getIdAlumno());
			
			return UtilXML.buildXmlGridCasosClinicosUsuarioAsignatura(getPortafoliosService().findCasosByPortafolio(p));
		
		
		
		
		
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
		/*UsuarioVO usuario = new UsuarioVO();
		usuario.setIdUsuario(f.getGr_id());
		getUsuariosService().delete(usuario);*/
	}

}
