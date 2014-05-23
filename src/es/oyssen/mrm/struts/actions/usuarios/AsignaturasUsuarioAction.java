package es.oyssen.mrm.struts.actions.usuarios;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletOutputStream;
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

public class AsignaturasUsuarioAction extends MrmAction {

	@Override
	public ActionForward process(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForm f = form;		
		String idUsuario = (String)request.getSession().getAttribute("idUsuario");
			
		UsuarioVO usuario = new UsuarioVO();
		UsuarioYPermisos usuarioYPermisos = new UsuarioYPermisos();
		usuario.setIdUsuario(idUsuario);
		usuario = getUsuariosService().findById(usuario);
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
