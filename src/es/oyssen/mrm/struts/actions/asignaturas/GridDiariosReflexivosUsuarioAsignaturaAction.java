package es.oyssen.mrm.struts.actions.asignaturas;

import es.oyssen.mrm.negocio.vo.PortafolioVO;
import es.oyssen.mrm.struts.actions.dhtmlx.DHTMLXGridAction;
import es.oyssen.mrm.struts.forms.asignaturas.GridDiariosReflexivosUsuarioAsignaturaForm;
import es.oyssen.mrm.struts.forms.dhtmlx.DhtmlxGridForm;
import es.oyssen.mrm.util.UtilXML;


public class GridDiariosReflexivosUsuarioAsignaturaAction extends DHTMLXGridAction {
	
	@Override
	public String search(DhtmlxGridForm f) throws Exception {
		
		
		GridDiariosReflexivosUsuarioAsignaturaForm form = (GridDiariosReflexivosUsuarioAsignaturaForm) f;
		
		
			
			PortafolioVO p = new PortafolioVO();	
			p.setAnyoAcademico(anyoAcademico);
			p.setIdAsignatura(form.getIdAsignatura());
			p.setIdAlumno(form.getIdAlumno());
			
			return UtilXML.buildXmlGridDiariosReflexivosUsuarioAsignatura(getPortafoliosService().findDiariosByPortafolio(p));
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
