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

import es.oyssen.mrm.negocio.vo.AsignaturaVO;
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
		AsignaturaVO asignatura = new AsignaturaVO();
		
		// para saber si es profesor o alumno
		String usuarioIdGrupo = (String)request.getSession().getAttribute("usuarioIdGrupo");
		
		
		//"Super Admin" o "Coordinador" o "Virtual Tour"
		if (usuarioIdGrupo.equals("1") || usuarioIdGrupo.equals("2") || usuarioIdGrupo.equals("5")) {
				
			}
		else
			// "Profesor"
			if (usuarioIdGrupo.equals("3")) {
				
			}
		else
			// "Alumno"
			if (usuarioIdGrupo.equals("4")) {
				
			}
		
		
		usuario = getUsuariosService().findById(usuario);
		
		
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
