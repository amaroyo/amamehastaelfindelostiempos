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
import es.oyssen.mrm.struts.Constantes;


public class LoginAction extends Action {
    private static Log log = LogFactory.getLog(LoginAction.class);
    
    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward execute(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        log.debug("BEGIN");
        HttpSession session = request.getSession();
        MessageResources messages = getResources(request);
        /*
        SesionUsuario sesionUsuario = new SesionUsuario();
        CredencialesSingleSignOn sso = (CredencialesSingleSignOn) session.getAttribute(SipConst.AUTENTICACION_SSO_KEY);		
		
        if (sso == null) {
            log.error("Autenticación SSO no encontrada");
            throw new AutenticacionException(messages.getMessage("error.autenticacion.notfound"));
        }
        if (sso.isAutenticado()) {
            Credenciales credenciales = new Credenciales();
            credenciales.setAplicacion(messages.getMessage("aplicacion.nombre"));
            credenciales.setIdSesion(sso.getSesion());
            credenciales.setUsuario(sso.getUsuario());
            sesionUsuario.setCredenciales(credenciales);
            Permisos permisos = new Permisos(sso.getPermisos());
			sesionUsuario.setPermisos(permisos);
			
			session.setAttribute("scopedTarget.credenciales", credenciales); //Metemos en sesion las credenciales para Spring

			log.info("Usuario autenticado: " + sesionUsuario.getCredenciales());
	        session.setAttribute(SipConst.SESION_USUARIO_KEY, sesionUsuario);
			
        } else {
            log.error("No Autenticado: " + sso.getMensaje());
            throw new AutenticacionException(sso.getMensaje());
        }
                */
        log.debug("END");
        return mapping.findForward(Constantes.FORWARD_SUCCESS_KEY);
    }
}
