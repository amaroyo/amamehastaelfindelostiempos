package es.oyssen.mrm.struts.actions.asignaturas;

import es.oyssen.mrm.negocio.vo.PortafolioVO;
import es.oyssen.mrm.negocio.vo.SeminarioRealizadoVO;
import es.oyssen.mrm.struts.actions.dhtmlx.DHTMLXGridAction;
import es.oyssen.mrm.struts.forms.asignaturas.GridUsuariosEstanciasForm;
import es.oyssen.mrm.struts.forms.asignaturas.GridUsuariosSeminariosAsignaturaForm;
import es.oyssen.mrm.struts.forms.dhtmlx.DhtmlxGridForm;
import es.oyssen.mrm.util.UtilXML;


public class GridUsuariosSeminariosAsignaturaAction extends DHTMLXGridAction {
	
	@Override
	public String search(DhtmlxGridForm f) throws Exception {
		
		
		GridUsuariosSeminariosAsignaturaForm form = (GridUsuariosSeminariosAsignaturaForm) f;
		
		//Si el usuario es coordinador, vemos todos los alumnos de ese anyo y esa asignatura


		//System.out.println(idUsuario);
		
		if (idGrupoUsuario.equals("1")){
			SeminarioRealizadoVO sr = new SeminarioRealizadoVO();
			sr.setIdSeminario(form.getIdSeminario());
			//vamos a crear un portafolio con el año academico actual para poder visualizar alumnos
			//que se hayan matriculado de esa asignatura en el año academico "actual"
			PortafolioVO p = new PortafolioVO();
			p.setAnyoAcademico(anyoAcademico);
			return UtilXML.buildXmlGridUsuariosSeminariosAsignatura(getSeminariosRealizadosService().findAllUsersByPortafolio(sr,p));
		}
		
		else return null;
		/*if (form.getIdGrupo() == null) {
			return UtilXML.buildXmlGridUsuarios(getUsuariosService().findAll());
		} else { 
			GrupoVO grupo = new GrupoVO();
			grupo.setIdGrupo(form.getIdGrupo());
			return UtilXML.buildXmlGridUsuariosGrupo(getUsuariosService().findByGrupo(grupo));
		}*/
		
		
		
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
