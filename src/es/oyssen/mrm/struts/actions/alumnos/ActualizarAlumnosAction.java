package es.oyssen.mrm.struts.actions.alumnos;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import es.oyssen.mrm.negocio.vo.InfoNombreTrabajoVO;
import es.oyssen.mrm.negocio.vo.PortafolioVO;
import es.oyssen.mrm.negocio.vo.ProfesorAsociadoVO;
import es.oyssen.mrm.struts.actions.MrmAction;

public class ActualizarAlumnosAction extends MrmAction {

	@Override
	public ActionForward process(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			
		
		
		
		String idUsuario = (String)request.getSession().getAttribute("idUsuario");
		String anyoAcademico = (String) request.getSession().getAttribute("anyoAcademico");
		String aceptados = (String)request.getParameter("aceptados");
		String noaceptados = (String)request.getParameter("noaceptados");
		
		String[] ac = aceptados.split(",");
		String[] nac = noaceptados.split(",");
		
		// Existe ese idUsuario en la tabla de Profesores?
		// si no se comprueba, salta excepcion de foreign constrain
		ProfesorAsociadoVO pa = new ProfesorAsociadoVO();
		pa.setIdProfesor(idUsuario);
		pa.setAnyoAcademico(anyoAcademico);
		
		List<ProfesorAsociadoVO> list = getProfesoresAsociadosService().findByProfesor(pa);
		
		if(!list.isEmpty()){
			for (int i = 0; i < ac.length; i++){
				PortafolioVO p = new PortafolioVO();
				p.setIdPortafolio(ac[i]);
				p.setIdProfesor(idUsuario);
				getPortafoliosService().update(p);
			}
			
			for (int i = 0; i < nac.length; i++){
				PortafolioVO p = new PortafolioVO();
				p.setIdPortafolio(nac[i]);
				p.setIdProfesor("1");
				getPortafoliosService().update(p);
			}
			
		}
		
		return mapping.findForward("success");
	}
	
	
	public String parseXML(Object o) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("<trabajos>");
		if (o != null){
			List<InfoNombreTrabajoVO> trabajos = (List<InfoNombreTrabajoVO>) o;
			Iterator<InfoNombreTrabajoVO> it = trabajos.iterator();
			while(it.hasNext()) {
				sb.append("<trabajo>");
				InfoNombreTrabajoVO trabajo = it.next();
				sb.append("<id><![CDATA[" + trabajo.getIdTrabajoInfo() + "]]></id>");
				sb.append("<nombre><![CDATA[" + trabajo.getNombre()  + "]]></nombre>");
				sb.append("</trabajo>");
			}	
		}
		sb.append("</trabajos>");
		return sb.toString();
	}

}
