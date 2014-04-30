package es.oyssen.mrm.struts.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

import es.oyssen.mrm.negocio.vo.Credenciales;
import es.oyssen.mrm.negocio.vo.Permisos;
import es.oyssen.mrm.negocio.vo.SesionUsuario;
import es.oyssen.mrm.struts.Constantes;


public class StandaloneLoginAction extends Action {
	
	private static Log log = LogFactory.getLog(StandaloneLoginAction.class);
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
        HttpSession session = request.getSession();
        MessageResources messages = getResources(request);
        SesionUsuario sesionUsuario = new SesionUsuario();

        Credenciales credenciales = new Credenciales();
        credenciales.setAplicacion(messages.getMessage("aplicacion.nombre"));
        credenciales.setIdSesion("1271856036045");
        credenciales.setUsuario("ADMIN");
        sesionUsuario.setCredenciales(credenciales);
        Permisos permisos = new Permisos(new String[]{"OPERADOR", "GRABADOR"});
		sesionUsuario.setPermisos(permisos);
        
        log.info("Usuario autenticado: " + sesionUsuario.getCredenciales());
        session.setAttribute(Constantes.SESION_USUARIO_KEY, sesionUsuario);
        
        Credenciales cred = (Credenciales) session.getAttribute("scopedTarget.credenciales");
        if (cred == null) {
        	cred = new Credenciales();
        	cred.setIdSesion("1271856036045");
        	cred.setUsuario("ADMIN");
            session.setAttribute("scopedTarget.credenciales", cred);
        }        
      
        log.debug("END");		
		return mapping.findForward("success");
	}
}
