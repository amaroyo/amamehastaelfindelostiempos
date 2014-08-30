package es.oyssen.mrm.struts.actions.permisos;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import es.oyssen.mrm.negocio.vo.GrupoVO;
import es.oyssen.mrm.negocio.vo.UsuarioPermisosVO;
import es.oyssen.mrm.struts.actions.MrmAction;
import es.oyssen.mrm.util.StringUtil;
import es.oyssen.mrm.util.UtilXML;

public class ListarPermisosAction extends MrmAction {

	
	@Override
	public ActionForward process(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {		
		
		String idGrupo = (String) request.getParameter("idGrupo");
		String idUsuario = (String) request.getParameter("idUsuario");
		String xml="";
		
		if(!StringUtil.isNullOrBlank(idGrupo)){
			GrupoVO g = new GrupoVO();
			g.setIdGrupo(idGrupo);			
			xml =  UtilXML.buildXmlComboPermisos(getPermisosService().findRestantes(g));

		}
		
		else if(!StringUtil.isNullOrBlank(idUsuario)) {
			UsuarioPermisosVO up = new UsuarioPermisosVO();
			up.setIdUsuario(idUsuario);
			
			xml = UtilXML.buildXmlComboPermisos(getUsuariosPermisosService().findRestantes(up));
		}
		
		response.setContentType("text/xml");
		ServletOutputStream sos = response.getOutputStream();
		sos.write(xml.getBytes("UTF-8"));
		sos.flush();
		sos.close();
		return mapping.findForward("success");
	}

}