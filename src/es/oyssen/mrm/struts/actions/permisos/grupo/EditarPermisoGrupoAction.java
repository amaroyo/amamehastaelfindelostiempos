package es.oyssen.mrm.struts.actions.permisos.grupo;

import es.oyssen.mrm.negocio.vo.GrupoPermisoVO;
import es.oyssen.mrm.struts.actions.dhtmlx.DHTMLXFormAction;
import es.oyssen.mrm.struts.forms.dhtmlx.DhtmlxForm;
import es.oyssen.mrm.struts.forms.permisos.grupo.PermisosGrupoForm;

public class EditarPermisoGrupoAction extends DHTMLXFormAction {

	@Override
	public Object load(DhtmlxForm f) throws Exception {
		return null;
	}

	@Override
	public String save(DhtmlxForm f) throws Exception {
		PermisosGrupoForm form = (PermisosGrupoForm) f;
		GrupoPermisoVO permisoGrupo = new GrupoPermisoVO();
		permisoGrupo.setIdPermiso(form.getIdPermiso());
		permisoGrupo.setIdGrupo(form.getIdGrupo());

		getGrupoPermisosService().insert(permisoGrupo);
		return null;
	}

	@Override
	public String parseXML(Object o) throws Exception {
		GrupoPermisoVO c = (GrupoPermisoVO) o;
		StringBuffer sb = new StringBuffer();
		sb.append("<data>");
		sb.append("<idPermiso><![CDATA[" + c.getIdPermiso() + "]]></idPermiso>");
		sb.append("<idGrupo><![CDATA[" + c.getIdGrupo() + "]]></idGrupo>");
		sb.append("</data>");
		
		return sb.toString();
	}

	@Override
	public String create(DhtmlxForm f) throws Exception {
		// TODO Auto-generated method stub
		return null;
		
	}

}
