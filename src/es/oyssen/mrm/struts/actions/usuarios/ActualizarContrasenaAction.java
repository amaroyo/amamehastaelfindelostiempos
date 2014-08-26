package es.oyssen.mrm.struts.actions.usuarios;

import java.io.PrintWriter;
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
		String correo = (String)request.getSession().getAttribute("correo");
		String oldPass = f.getOldPass();
		String newPass = f.getNewPass1();
			
		UsuarioVO usuario = new UsuarioVO();
		UsuarioYPermisos usuarioYPermisos = new UsuarioYPermisos();
		usuario.setCorreo(correo);
		usuario.setContrasenya(EncriptarUtil.getStringMessageDigest(oldPass, EncriptarUtil.MD5));
		usuario = getUsuariosService().findByCorreoPass(usuario);	
		usuarioYPermisos.setUsuario(usuario);	
		
		
		//application/json or application/xml text/html
		response.setContentType("text/xml;charset=utf-8");
		PrintWriter out;
	    out = response.getWriter();
		if (usuario != null){
			usuario.setContrasenya(EncriptarUtil.getStringMessageDigest(newPass, EncriptarUtil.MD5));
			getUsuariosService().update(usuario);
			out.print("password changed");
		}
		else {
			out.print("password not changed: incorrect password");
		}
		
		out.flush();
		out.close();
		return mapping.findForward("success");
	}

}
