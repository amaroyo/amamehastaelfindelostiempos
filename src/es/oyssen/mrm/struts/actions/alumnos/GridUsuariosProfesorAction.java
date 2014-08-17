package es.oyssen.mrm.struts.actions.alumnos;

import es.oyssen.mrm.negocio.vo.GrupoVO;
import es.oyssen.mrm.negocio.vo.PortafolioVO;
import es.oyssen.mrm.negocio.vo.UsuarioVO;
import es.oyssen.mrm.struts.actions.dhtmlx.DHTMLXGridAction;
import es.oyssen.mrm.struts.forms.alumnos.GridUsuariosProfesorForm;
import es.oyssen.mrm.struts.forms.dhtmlx.DhtmlxGridForm;
import es.oyssen.mrm.struts.forms.usuarios.GridUsuariosForm;
import es.oyssen.mrm.util.StringUtil;
import es.oyssen.mrm.util.UtilXML;


public class GridUsuariosProfesorAction extends DHTMLXGridAction {
	
	@Override
	public String search(DhtmlxGridForm f) throws Exception {
		
		GridUsuariosProfesorForm form = (GridUsuariosProfesorForm) f;
		
		String busqueda="";
		if(!StringUtil.isNullOrBlank(form.getBusqueda())) busqueda=form.getBusqueda();
			

		if (busqueda.equals("si")){
			PortafolioVO p = new PortafolioVO();
			p.setAnyoAcademico(anyoAcademico);
			p.setIdProfesor(idUsuario);
			return UtilXML.buildXmlGridUsuariosProfesores(getUsuariosService().findAllbyProfesorDemas(p),idUsuario);
		}
		else if (idGrupoUsuario.equals("1") || idGrupoUsuario.equals("2")){
			PortafolioVO p = new PortafolioVO();
			p.setAnyoAcademico(anyoAcademico);
			return UtilXML.buildXmlGridUsuariosProfesores(getUsuariosService().findAllbyAnyoAcademico(p),"");
		}		
		else if (idGrupoUsuario.equals("3")) {
			PortafolioVO p = new PortafolioVO();
			p.setAnyoAcademico(anyoAcademico);
			p.setIdProfesor(idUsuario);
			return UtilXML.buildXmlGridUsuariosProfesores(getUsuariosService().findAllbyProfesor(p),idUsuario);
		} 
		else{ 
			return "";
		}
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
		UsuarioVO usuario = new UsuarioVO();
		usuario.setIdUsuario(f.getGr_id());
		getUsuariosService().delete(usuario);
	}

}
