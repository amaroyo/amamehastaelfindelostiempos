package es.oyssen.mrm.struts.actions.permisos.usuario;

import es.oyssen.mrm.negocio.vo.UsuarioPermisosVO;
import es.oyssen.mrm.negocio.vo.UsuarioVO;
import es.oyssen.mrm.struts.actions.dhtmlx.DHTMLXGridAction;
import es.oyssen.mrm.struts.forms.dhtmlx.DhtmlxGridForm;
import es.oyssen.mrm.struts.forms.permisos.usuario.GridPermisosUsuarioForm;
import es.oyssen.mrm.util.UtilXML;

public class GridPermisosUsuarioAction extends DHTMLXGridAction {

	@Override
	public String search(DhtmlxGridForm f) throws Exception {
		
		GridPermisosUsuarioForm form = (GridPermisosUsuarioForm) f;
		
		UsuarioVO u = new UsuarioVO();
		u.setIdUsuario(form.getIdUsuario());
		
		return UtilXML.buildXmlGridPermisosUsuario(getUsuariosPermisosService().findByUsuario(u), u);
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
		
		UsuarioPermisosVO up = new UsuarioPermisosVO();
		
		String[] sp = f.getGr_id().split("-");
		up.setIdPermiso(sp[0]);
		up.setIdUsuario(sp[1]);
		getUsuariosPermisosService().delete(up);
	}

}
