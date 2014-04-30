package es.oyssen.mrm.struts.actions;

import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import es.oyssen.mrm.negocio.vo.Credenciales;
import es.oyssen.mrm.negocio.vo.SesionUsuario;
import es.oyssen.mrm.struts.Constantes;

/**
 * Clase Base que manipula el Contexto de la Aplicacion.
 * 
 * @author oyssen
 * @version 1.0
 */
public abstract class SpringBaseAction extends Action {
	
	private static final String RESOURCE = "es/analysiq/analystdesktop/struts/ApplicationResources";

	protected final Log log = LogFactory.getLog(getClass());
	
	public SesionUsuario getSesionUsuario(HttpServletRequest request) {
		HttpSession session = request.getSession();
		SesionUsuario sesion = (SesionUsuario) session.getAttribute(Constantes.SESION_USUARIO_KEY);
		return sesion;
	}
	
	public Credenciales getCredencialesUsuario(HttpServletRequest request) {
		return getSesionUsuario(request).getCredenciales();
	}
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return process(mapping, form, request, response);
	}
	
	public abstract ActionForward process(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception;

    /**
     * Metodo para obtener los beans inicializados por Spring.
     *
     * @param name Nombre del bean de Spring a devolver.
     * @return Object bean de ApplicationContext.
     */
    public Object getBean(String name) {
        ApplicationContext ctx = 
            WebApplicationContextUtils.getRequiredWebApplicationContext(servlet.getServletContext());

        return ctx.getBean(name);
    }
    
    /**
     * Metodo para obtener el mensaje asociado a una clave en el ApplicationResources.
     *
     * @param String - Nombre de la key a ser devuelta.
     * @return String - Valor asociado a la key.
     */
    public String getMessageOfResouces(String key) {
    	
    	ResourceBundle resourceBundle = ResourceBundle.getBundle(RESOURCE);
   		return resourceBundle.getString(key);
    }

    
}
