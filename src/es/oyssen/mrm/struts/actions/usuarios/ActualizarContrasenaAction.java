package es.oyssen.mrm.struts.actions.usuarios;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import es.oyssen.mrm.negocio.vo.UsuarioVO;
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
		usuario.setUser(user);
		usuario.setPass(EncriptarUtil.getStringMessageDigest(oldPass, EncriptarUtil.MD5));
		usuario = getUsuariosService().findByUserPass(usuario);		
		
		if (usuario != null){
			usuario.setPass(EncriptarUtil.getStringMessageDigest(newPass, EncriptarUtil.MD5));
			getUsuariosService().update(usuario);
		}				
		
		return mapping.findForward("success");
	}

}
