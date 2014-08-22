package es.oyssen.mrm.struts.actions.administrar;

import es.oyssen.mrm.negocio.vo.ErrorLogVO;
import es.oyssen.mrm.negocio.vo.GrupoVO;
import es.oyssen.mrm.negocio.vo.PortafolioVO;
import es.oyssen.mrm.negocio.vo.UsuarioVO;
import es.oyssen.mrm.struts.actions.dhtmlx.DHTMLXGridAction;
import es.oyssen.mrm.struts.forms.administrar.GridRegistroErroresForm;
import es.oyssen.mrm.struts.forms.alumnos.GridUsuariosProfesorForm;
import es.oyssen.mrm.struts.forms.dhtmlx.DhtmlxGridForm;
import es.oyssen.mrm.struts.forms.usuarios.GridUsuariosForm;
import es.oyssen.mrm.util.StringUtil;
import es.oyssen.mrm.util.UtilXML;


public class GridRegistroErroresAction extends DHTMLXGridAction {
	
	@Override
	public String search(DhtmlxGridForm f) throws Exception {
		
		GridRegistroErroresForm form = (GridRegistroErroresForm) f;
		
		return UtilXML.buildXmlRegistroErrores(getErroresLogService().findAll());
		
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
		
	}

}
