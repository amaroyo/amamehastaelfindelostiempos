package es.oyssen.mrm.struts.actions.asignaturas;

import es.oyssen.mrm.negocio.vo.PortafolioVO;
import es.oyssen.mrm.struts.actions.dhtmlx.DHTMLXGridAction;
import es.oyssen.mrm.struts.forms.asignaturas.GridSeminariosUsuarioForm;
import es.oyssen.mrm.struts.forms.dhtmlx.DhtmlxGridForm;
import es.oyssen.mrm.util.UtilXML;


public class GridSeminariosUsuarioAction extends DHTMLXGridAction {
	
	@Override
	public String search(DhtmlxGridForm f) throws Exception {
		
		
		GridSeminariosUsuarioForm form = (GridSeminariosUsuarioForm) f;
		
			
			PortafolioVO p = new PortafolioVO();
			p.setIdAlumno(form.getIdAlumno());
			p.setIdAsignatura(form.getIdAsignatura());
			
			if(form.getPeticion().equals("realizados"))
				return UtilXML.buildXmlGridSeminariosRealizadosUsuario(getSeminariosRealizadosService().findSeminariosRealizados(p));
		
			else if (form.getPeticion().equals("pendientes"))
				return UtilXML.buildXmlGridSeminariosPendientesUsuario(getSeminariosRealizadosService().findSeminariosPendientes(p));
			
			else return "";
		
		
		
		
		
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
