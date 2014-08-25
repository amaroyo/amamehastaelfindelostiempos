package es.oyssen.mrm.struts.actions.asignaturas;

import es.oyssen.mrm.negocio.vo.AnexoVO;
import es.oyssen.mrm.negocio.vo.PortafolioVO;
import es.oyssen.mrm.negocio.vo.SeminarioRealizadoVO;
import es.oyssen.mrm.negocio.vo.TrabajoDeCampoVO;
import es.oyssen.mrm.struts.actions.dhtmlx.DHTMLXGridAction;
import es.oyssen.mrm.struts.forms.asignaturas.GridAnexos2UsuarioAsignaturaForm;
import es.oyssen.mrm.struts.forms.asignaturas.GridCasosClinicosUsuarioAsignaturaForm;
import es.oyssen.mrm.struts.forms.asignaturas.GridTrabajosCampoUsuarioAsignaturaForm;
import es.oyssen.mrm.struts.forms.asignaturas.GridUsuariosEstanciasForm;
import es.oyssen.mrm.struts.forms.asignaturas.GridUsuariosSeminariosAsignaturaForm;
import es.oyssen.mrm.struts.forms.asignaturas.GridUsuariosTrabajosCampoAsignaturaForm;
import es.oyssen.mrm.struts.forms.dhtmlx.DhtmlxGridForm;
import es.oyssen.mrm.util.StringUtil;
import es.oyssen.mrm.util.UtilXML;


public class GridAnexos2UsuarioAsignaturaAction extends DHTMLXGridAction {
	
	@Override
	public String search(DhtmlxGridForm f) throws Exception {
		
		
		GridAnexos2UsuarioAsignaturaForm form = (GridAnexos2UsuarioAsignaturaForm) f;
		
		
	
		if(!StringUtil.isNullOrBlank(form.getIdPortafolio())){	
			AnexoVO a = new AnexoVO();
			a.setIdPortafolio(form.getIdPortafolio());
			return UtilXML.buildXmlGridAnexos2UsuarioAsignatura(getAnexosService().findAllByPortafolio(a));
			
		}
		
		else {
			PortafolioVO p = new PortafolioVO();	
			p.setAnyoAcademico(anyoAcademico);
			p.setIdAsignatura(form.getIdAsignatura());
			p.setIdAlumno(form.getIdAlumno());
			p=getPortafoliosService().findByAlumnoAsignatura(p);
			AnexoVO a = new AnexoVO();
			a.setIdPortafolio(p.getIdPortafolio());
			return UtilXML.buildXmlGridAnexos2UsuarioAsignatura(getAnexosService().findAllByPortafolio(a));
			
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
		/*UsuarioVO usuario = new UsuarioVO();
		usuario.setIdUsuario(f.getGr_id());
		getUsuariosService().delete(usuario);*/
	}

}
