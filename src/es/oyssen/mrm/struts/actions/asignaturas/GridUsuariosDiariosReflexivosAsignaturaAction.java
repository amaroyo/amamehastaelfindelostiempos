package es.oyssen.mrm.struts.actions.asignaturas;

import es.oyssen.mrm.negocio.vo.PortafolioVO;
import es.oyssen.mrm.struts.actions.dhtmlx.DHTMLXGridAction;
import es.oyssen.mrm.struts.forms.asignaturas.GridUsuariosDiariosReflexivosAsignaturaForm;
import es.oyssen.mrm.struts.forms.dhtmlx.DhtmlxGridForm;
import es.oyssen.mrm.util.UtilXML;


public class GridUsuariosDiariosReflexivosAsignaturaAction extends DHTMLXGridAction {
	
	@Override
	public String search(DhtmlxGridForm f) throws Exception {
		
		
		GridUsuariosDiariosReflexivosAsignaturaForm form = (GridUsuariosDiariosReflexivosAsignaturaForm) f;
		
		
		PortafolioVO p = new PortafolioVO();
		p.setIdAsignatura(form.getIdAsignatura());
		p.setAnyoAcademico(anyoAcademico);
		return UtilXML.buildXmlGridUsuariosPortafolioAsignatura(getPortafoliosService().findUsuariosByAsignatura(p));
	
		
		
		
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
