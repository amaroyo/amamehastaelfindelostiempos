package es.oyssen.mrm.struts.actions.grupos;

import es.oyssen.mrm.struts.actions.dhtmlx.DHTMLXGridAction;
import es.oyssen.mrm.struts.forms.dhtmlx.DhtmlxGridForm;
import es.oyssen.mrm.util.UtilXML;

public class GridGruposAction extends DHTMLXGridAction {

	@Override
	public String search(DhtmlxGridForm f) throws Exception {
		return UtilXML.buildXmlGridGrupos(getGruposService().findAll());
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
