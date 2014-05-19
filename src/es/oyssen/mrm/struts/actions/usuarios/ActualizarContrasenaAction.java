package es.oyssen.mrm.struts.actions.usuarios;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import es.oyssen.mrm.negocio.vo.UsuarioVO;
import es.oyssen.mrm.negocio.vo.UsuarioYPermisos;
import es.oyssen.mrm.struts.actions.MrmAction;
import es.oyssen.mrm.struts.forms.usuarios.EditarContrasenaForm;
import es.oyssen.mrm.util.EncriptarUtil;

public class ActualizarContrasenaAction extends MrmAction {

	@Override
	public ActionForward process(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		EditarContrasenaForm f = (EditarContrasenaForm) form;		
		String user = (String)request.getSession().getAttribute("usuario");
		String oldPass = f.getOldPass();
		String newPass = f.getNewPass();
			
		UsuarioVO usuario = new UsuarioVO();
		UsuarioYPermisos usuarioYPermisos = new UsuarioYPermisos();
		usuario.setCorreo(user);
		usuario.setContrasenya(EncriptarUtil.getStringMessageDigest(oldPass, EncriptarUtil.MD5));
		usuario = getUsuariosService().findByCorreoPass(usuario);	
		usuarioYPermisos.setUsuario(usuario);	
		
		if (usuario != null){
			usuario.setContrasenya(EncriptarUtil.getStringMessageDigest(newPass, EncriptarUtil.MD5));
			getUsuariosService().update(usuario);
		}				
		request.getSession().setAttribute("usuarioYPermisos", parseXML(usuarioYPermisos));
		return mapping.findForward("success");
	}
	
	private static final String parseXML(Object o) throws Exception {
		UsuarioYPermisos c = (UsuarioYPermisos) o;
		StringBuffer sb = new StringBuffer();
		sb.append("<data>");
		sb.append("<update_password_correct>" + ((c.getUsuario() == null) ? "NO" : "YES") + "</update_password_correct>");
		sb.append("</data>");
		return sb.toString();
	}

}
