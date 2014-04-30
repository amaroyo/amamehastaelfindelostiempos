package es.oyssen.mrm.struts.actions.servicios.usuario;

import es.oyssen.mrm.negocio.vo.GrupoVO;
import es.oyssen.mrm.negocio.vo.PermisoGrupoVO;
import es.oyssen.mrm.negocio.vo.ServicioUsuarioVO;
import es.oyssen.mrm.negocio.vo.UsuarioVO;
import es.oyssen.mrm.struts.actions.dhtmlx.DHTMLXGridAction;
import es.oyssen.mrm.struts.forms.dhtmlx.DhtmlxGridForm;
import es.oyssen.mrm.struts.forms.permisos.grupo.GridPermisosGrupoForm;
import es.oyssen.mrm.struts.forms.servicios.usuario.GridServiciosUsuarioForm;
import es.oyssen.mrm.util.UtilXML;

public class GridServiciosUsuarioAction extends DHTMLXGridAction {

	@Override
	public String search(DhtmlxGridForm f) throws Exception {
		GridServiciosUsuarioForm form = (GridServiciosUsuarioForm) f;
		UsuarioVO usuario = new UsuarioVO();
		usuario.setIdUsuario(form.getIdUsuario());						
		return UtilXML.buildXmlGridServiciosUsuario(getServiciosUsuarioService().findByUsuario(usuario));
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
		ServicioUsuarioVO servicioUsuario = new ServicioUsuarioVO();
		servicioUsuario.setIdServicioUsuario(f.getGr_id());
		getServiciosUsuarioService().delete(servicioUsuario);	
	}

}
