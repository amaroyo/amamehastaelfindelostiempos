package es.oyssen.mrm.struts.actions.usuarios;

import es.oyssen.mrm.negocio.vo.GrupoVO;
import es.oyssen.mrm.negocio.vo.UsuarioVO;
import es.oyssen.mrm.struts.actions.dhtmlx.DHTMLXGridAction;
import es.oyssen.mrm.struts.forms.dhtmlx.DhtmlxGridForm;
import es.oyssen.mrm.struts.forms.usuarios.GridUsuariosForm;
import es.oyssen.mrm.util.UtilXML;


public class GridUsuariosAction extends DHTMLXGridAction {
	
	@Override
	public String search(DhtmlxGridForm f) throws Exception {
		GridUsuariosForm form = (GridUsuariosForm) f;
		if (form.getIdGrupo() == null) {
			return UtilXML.buildXmlGridUsuarios(getUsuariosService().findAll());
		} else { 
			GrupoVO grupo = new GrupoVO();
			grupo.setIdGrupo(form.getIdGrupo());
			return UtilXML.buildXmlGridUsuariosGrupo(getUsuariosService().findByGrupo(grupo));
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
