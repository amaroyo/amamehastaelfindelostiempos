package es.oyssen.mrm.struts.actions.asignaturas;

import es.oyssen.mrm.negocio.vo.PortafolioVO;
import es.oyssen.mrm.negocio.vo.SeminarioRealizadoVO;
import es.oyssen.mrm.negocio.vo.TrabajoDeCampoVO;
import es.oyssen.mrm.struts.actions.dhtmlx.DHTMLXGridAction;
import es.oyssen.mrm.struts.forms.asignaturas.GridUsuariosEstanciasForm;
import es.oyssen.mrm.struts.forms.asignaturas.GridUsuariosSeminariosAsignaturaForm;
import es.oyssen.mrm.struts.forms.asignaturas.GridUsuariosTrabajosCampoAsignaturaForm;
import es.oyssen.mrm.struts.forms.dhtmlx.DhtmlxGridForm;
import es.oyssen.mrm.util.UtilXML;


public class GridUsuariosTrabajosCampoAsignaturaAction extends DHTMLXGridAction {
	
	@Override
	public String search(DhtmlxGridForm f) throws Exception {
		
		
		GridUsuariosTrabajosCampoAsignaturaForm form = (GridUsuariosTrabajosCampoAsignaturaForm) f;
		
		
		TrabajoDeCampoVO t = new TrabajoDeCampoVO();
		PortafolioVO p = new PortafolioVO();
		t.setIdTrabajoInfo(form.getIdTrabajoInfo());
		p.setAnyoAcademico(anyoAcademico);
		p.setIdAsignatura(form.getIdAsignatura());
		
		return UtilXML.buildXmlGridUsuariosTrabajosCampoAsignatura(getTrabajosDeCampoService().findAllByAsignaturaTrabajo(p,t));
	
		
		
		
		
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
