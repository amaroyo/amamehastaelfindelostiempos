package es.oyssen.mrm.struts.actions.permisos.grupo;

import es.oyssen.mrm.negocio.vo.CanalVO;
import es.oyssen.mrm.negocio.vo.PermisoGrupoVO;
import es.oyssen.mrm.struts.actions.dhtmlx.DHTMLXFormAction;
import es.oyssen.mrm.struts.forms.canales.CanalForm;
import es.oyssen.mrm.struts.forms.dhtmlx.DhtmlxForm;
import es.oyssen.mrm.struts.forms.permisos.grupo.PermisosGrupoForm;
import es.oyssen.mrm.util.StringUtil;

public class EditarPermisoGrupoAction extends DHTMLXFormAction {

	@Override
	public Object load(DhtmlxForm f) throws Exception {
		return null;
	}

	@Override
	public void save(DhtmlxForm f) throws Exception {
		PermisosGrupoForm form = (PermisosGrupoForm) f;
		PermisoGrupoVO permisoGrupo = new PermisoGrupoVO();
		permisoGrupo.setIdPermiso(form.getIdPermiso());
		permisoGrupo.setIdGrupo(form.getIdGrupo());

		getPermisosGrupoService().insert(permisoGrupo);
	}

	@Override
	public String parseXML(Object o) throws Exception {
		PermisoGrupoVO c = (PermisoGrupoVO) o;
		StringBuffer sb = new StringBuffer();
		sb.append("<data>");
		sb.append("<idPermisoGrupo><![CDATA[" + c.getIdPermisoGrupo() + "]]></idPermisoGrupo>");
		sb.append("<idPermiso><![CDATA[" + c.getIdPermiso() + "]]></idPermiso>");
		sb.append("<idGrupo><![CDATA[" + c.getIdGrupo() + "]]></idGrupo>");
		sb.append("<nombre><![CDATA[" + c.getNombre() + "]]></nombre>");
		sb.append("</data>");
		
		return sb.toString();
	}

	@Override
	public void create(DhtmlxForm f) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
