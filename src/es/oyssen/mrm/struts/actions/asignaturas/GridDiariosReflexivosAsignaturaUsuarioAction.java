package es.oyssen.mrm.struts.actions.asignaturas;

import es.oyssen.mrm.negocio.vo.CasoClinicoVO;
import es.oyssen.mrm.negocio.vo.DiarioReflexivoVO;
import es.oyssen.mrm.negocio.vo.PortafolioVO;
import es.oyssen.mrm.negocio.vo.SeminarioRealizadoVO;
import es.oyssen.mrm.struts.actions.dhtmlx.DHTMLXGridAction;
import es.oyssen.mrm.struts.forms.asignaturas.GridCasosClinicosAsignaturaUsuarioForm;
import es.oyssen.mrm.struts.forms.asignaturas.GridDiariosReflexivosAsignaturaUsuarioForm;
import es.oyssen.mrm.struts.forms.asignaturas.GridUsuariosCasosClinicosAsignaturaForm;
import es.oyssen.mrm.struts.forms.asignaturas.GridUsuariosEstanciasForm;
import es.oyssen.mrm.struts.forms.asignaturas.GridUsuariosSeminariosAsignaturaForm;
import es.oyssen.mrm.struts.forms.dhtmlx.DhtmlxGridForm;
import es.oyssen.mrm.util.UtilXML;


public class GridDiariosReflexivosAsignaturaUsuarioAction extends DHTMLXGridAction {
	
	@Override
	public String search(DhtmlxGridForm f) throws Exception {
		
		
		GridDiariosReflexivosAsignaturaUsuarioForm form = (GridDiariosReflexivosAsignaturaUsuarioForm) f;
		
		DiarioReflexivoVO d = new DiarioReflexivoVO();
		d.setIdPortafolio(form.getIdPortafolio());
	
		return UtilXML.buildXmlGridDiariosReflexivosAsignaturaUsuario(getDiariosReflexivosService().findAllByPortafolio(d));
	
		
		
		
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
