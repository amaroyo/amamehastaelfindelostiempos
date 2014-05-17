package es.oyssen.mrm.struts.actions.usuarios;

import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import es.oyssen.mrm.negocio.vo.ComercialVO;
import es.oyssen.mrm.negocio.vo.DistribuidorVO;
import es.oyssen.mrm.negocio.vo.GrupoVO;
import es.oyssen.mrm.negocio.vo.LogUsuarioVO;
import es.oyssen.mrm.negocio.vo.PermisoGrupoVO;
import es.oyssen.mrm.negocio.vo.UsuarioVO;
import es.oyssen.mrm.negocio.vo.UsuarioYPermisos;
import es.oyssen.mrm.struts.actions.MrmAction;
import es.oyssen.mrm.struts.actions.dhtmlx.DHTMLXFormAction;
import es.oyssen.mrm.struts.forms.dhtmlx.DhtmlxForm;
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
		usuario.setUser(f.getUser());
		usuario.setPass(EncriptarUtil.getStringMessageDigest(f.getPass(), EncriptarUtil.MD5));
		usuario = getUsuariosService().findByUserPass(usuario);
		usuarioYPermisos.setUsuario(usuario);		
		
		if (usuario != null){
			GrupoVO grupo = new GrupoVO();
			grupo.setIdGrupo(usuario.getIdGrupo());
			List<PermisoGrupoVO> permisos = getPermisosGrupoService().findByGrupo(grupo);
			usuarioYPermisos.setPermisos(permisos);
		}			
		
		if (usuario != null){
			LogUsuarioVO logUsuario = new LogUsuarioVO();
			logUsuario.setIdUsuario(usuario.getIdUsuario());
			getLogsUsuarioService().insert(logUsuario);
		}
		
		if (usuario != null){
			request.getSession().setAttribute("usuarioIdGrupo", usuario.getIdGrupo());
			request.getSession().setAttribute("idUsuario", usuario.getIdUsuario());
			request.getSession().setAttribute("usuario", usuario.getUser());
			request.getSession().setAttribute("anyo_academico", anyo_academico());
		
			if (usuario.getIdGrupo().equals("1")) {
				usuarioYPermisos.setBloqueado("NO");
			} else if (usuario.getIdGrupo().equals("2")) {
				request.getSession().setAttribute("usuarioIdCanal", usuario.getIdAsociado());
				request.getSession().setAttribute("usuarioIdDistribuidor", "NO");
				request.getSession().setAttribute("usuarioIdComercial", "NO");
				usuarioYPermisos.setBloqueado("NO");
			} else if (usuario.getIdGrupo().equals("3")) {
				request.getSession().setAttribute("usuarioIdDistribuidor", usuario.getIdAsociado());
				request.getSession().setAttribute("usuarioIdComercial", "NO");
				DistribuidorVO distribuidor = new DistribuidorVO();
				distribuidor.setIdDistribuidor(usuario.getIdAsociado());
				distribuidor = getDistribuidoresService().findById(distribuidor);
				request.getSession().setAttribute("usuarioIdCanal", distribuidor.getIdCanal());
				
				usuarioYPermisos.setBloqueado(distribuidor.getBloqueado());
			} else if (usuario.getIdGrupo().equals("4")) {
				request.getSession().setAttribute("usuarioIdComercial", usuario.getIdAsociado());
				ComercialVO comercial = new ComercialVO();
				comercial.setIdComercial(usuario.getIdAsociado());
				comercial = getComercialesService().findById(comercial);
				request.getSession().setAttribute("usuarioIdCanal", comercial.getIdCanal());
				request.getSession().setAttribute("usuarioIdDistribuidor", comercial.getIdDistribuidor());	
				
				DistribuidorVO distribuidor = new DistribuidorVO();
				distribuidor.setIdDistribuidor(comercial.getIdDistribuidor());
				distribuidor = getDistribuidoresService().findById(distribuidor);
				usuarioYPermisos.setBloqueado(distribuidor.getBloqueado());
			} else if (usuario.getIdGrupo().equals("5")) {
				usuarioYPermisos.setBloqueado("NO");
				request.getSession().setAttribute("usuarioIdCanal", "NO");
				request.getSession().setAttribute("usuarioIdDistribuidor", "NO");
				request.getSession().setAttribute("usuarioIdComercial", "NO");
			}
		}

		request.getSession().setAttribute("usuarioYPermisos", parseXML(usuarioYPermisos));
		
		request.getSession().setAttribute("filtrarEstadoWon", "false");
		request.getSession().setAttribute("filtrarEstadoLost", "true");
		request.getSession().setAttribute("creacionDesde", "");
		request.getSession().setAttribute("creacionHasta", "");
		request.getSession().setAttribute("modificacionDesde", "");
		request.getSession().setAttribute("modificacionHasta", "");
		
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
		if (c.getBloqueado() != null) 
			sb.append("<bloqueado>" + c.getBloqueado() + "</bloqueado>");
		if (c.getPermisos() != null) {
			sb.append("<permisos>");
			for (PermisoGrupoVO permiso : c.getPermisos())
				sb.append("<permiso>" + permiso.getIdPermiso() + "</permiso>");
			sb.append("</permisos>");
		}
		sb.append("</data>");
		
		return sb.toString();
	}
	
	private String anyo_academico(){
		String actual = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
		String pasado = String.valueOf(Calendar.getInstance().get(Calendar.YEAR)-1);
		return pasado + "/" + actual;
	}

}
