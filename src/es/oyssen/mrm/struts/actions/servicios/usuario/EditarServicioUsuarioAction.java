package es.oyssen.mrm.struts.actions.servicios.usuario;

import es.oyssen.mrm.negocio.vo.ServicioUsuarioVO;
import es.oyssen.mrm.struts.actions.dhtmlx.DHTMLXFormAction;
import es.oyssen.mrm.struts.forms.dhtmlx.DhtmlxForm;
import es.oyssen.mrm.struts.forms.servicios.usuario.ServiciosUsuarioForm;


public class EditarServicioUsuarioAction extends DHTMLXFormAction {

	@Override
	public Object load(DhtmlxForm f) throws Exception {
		return null;
	}

	@Override
	public String save(DhtmlxForm f) throws Exception {
		ServiciosUsuarioForm form = (ServiciosUsuarioForm) f;
		ServicioUsuarioVO servicioUsuario = new ServicioUsuarioVO();
		servicioUsuario.setIdServicio(form.getIdServicio());
		servicioUsuario.setIdUsuario(form.getIdUsuario());

		getServiciosUsuarioService().insert(servicioUsuario);
		return null;
	}

	@Override
	public String parseXML(Object o) throws Exception {
		ServicioUsuarioVO c = (ServicioUsuarioVO) o;
		StringBuffer sb = new StringBuffer();
		sb.append("<data>");
		sb.append("<idServicioUsuario><![CDATA[" + c.getIdServicioUsuario() + "]]></idServicioUsuario>");
		sb.append("<idServicio><![CDATA[" + c.getIdServicio() + "]]></idServicio>");
		sb.append("<idUsuario><![CDATA[" + c.getIdUsuario() + "]]></idUsuario>");
		sb.append("</data>");
		
		return sb.toString();
	}

	@Override
	public String create(DhtmlxForm f) throws Exception {
		// TODO Auto-generated method stub
		return null;
		
	}

}
