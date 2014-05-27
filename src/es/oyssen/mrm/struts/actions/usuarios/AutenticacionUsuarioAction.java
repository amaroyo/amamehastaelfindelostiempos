package es.oyssen.mrm.struts.actions.usuarios;

import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import es.oyssen.mrm.negocio.vo.GrupoVO;
import es.oyssen.mrm.negocio.vo.LogUsuarioVO;
import es.oyssen.mrm.negocio.vo.GrupoPermisoVO;
import es.oyssen.mrm.negocio.vo.UsuarioVO;
import es.oyssen.mrm.negocio.vo.UsuarioYPermisos;
import es.oyssen.mrm.struts.actions.MrmAction;
import es.oyssen.mrm.struts.forms.usuarios.AutenticacionUsuarioForm;
import es.oyssen.mrm.util.EncriptarUtil;

public class AutenticacionUsuarioAction extends MrmAction {

	@Override
	public ActionForward process(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		AutenticacionUsuarioForm f = (AutenticacionUsuarioForm) form;		
		UsuarioYPermisos usuarioYPermisos = new UsuarioYPermisos();
			
		UsuarioVO usuario = new UsuarioVO();
		usuario.setCorreo(f.getCorreo());
		usuario.setContrasenya(EncriptarUtil.getStringMessageDigest(f.getPass(), EncriptarUtil.MD5));
		usuario = getUsuariosService().findByCorreoPass(usuario);	
		
		if (usuario != null){
			usuarioYPermisos.setUsuario(usuario);	
			GrupoVO grupo = new GrupoVO();
			grupo.setIdGrupo(usuario.getIdGrupo());
			List<GrupoPermisoVO> permisos = getGrupoPermisosService().findByGrupo(grupo);
			usuarioYPermisos.setPermisos(permisos);
		 
			LogUsuarioVO logUsuario = new LogUsuarioVO();
			logUsuario.setIdUsuario(usuario.getIdUsuario());
			getLogsUsuarioService().insert(logUsuario);


			request.getSession().setAttribute("idGrupoUsuario", usuario.getIdGrupo());
			request.getSession().setAttribute("idUsuario", usuario.getIdUsuario());
			request.getSession().setAttribute("correo", usuario.getCorreo());
			request.getSession().setAttribute("anyoAcademico", anyoAcademico());
		}

		request.getSession().setAttribute("usuarioYPermisos", parseXML(usuarioYPermisos));
		
		return mapping.findForward("success");
	}
	

	private static final String parseXML(Object o) throws Exception {
		UsuarioYPermisos c = (UsuarioYPermisos) o;
		StringBuffer sb = new StringBuffer();
		sb.append("<data>");
		sb.append("<existe>" + ((c.getUsuario() == null) ? "NO" : "YES") + "</existe>");		
		if (c.getUsuario() != null){
			sb.append("<grupo>" + c.getUsuario().getIdGrupo() + "</grupo>");
			sb.append("<nombre>" + c.getUsuario().getNombre() + "</nombre>");
		}
		if (c.getPermisos() != null) {
			sb.append("<permisos>");
			for (GrupoPermisoVO permiso : c.getPermisos())
				sb.append("<permiso>" + permiso.getIdPermiso() + "</permiso>");
			sb.append("</permisos>");
		}
		sb.append("</data>");
		
		return sb.toString();
	}
	
	private String anyoAcademico(){
		String actual = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
		String pasado = String.valueOf(Calendar.getInstance().get(Calendar.YEAR)-1);
		return pasado + "/" + actual;
	}

}
