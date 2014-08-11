package es.oyssen.mrm.struts.actions.cursos;

import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import es.oyssen.mrm.negocio.vo.AsignaturaVO;
import es.oyssen.mrm.negocio.vo.CriterioRubricaVO;
import es.oyssen.mrm.negocio.vo.GrupoCriteriosRubricasVO;
import es.oyssen.mrm.negocio.vo.GruposCriteriosRubricaAsignaturaVO;
import es.oyssen.mrm.struts.actions.MrmAction;
import es.oyssen.mrm.struts.forms.cursos.CrearAsignaturaForm;
import es.oyssen.mrm.struts.forms.cursos.EditarAsignaturaForm;

public class GruposCriteriosAsignaturaAction extends MrmAction {

	@Override
	public ActionForward process(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		EditarAsignaturaForm f = (EditarAsignaturaForm) form;
		GrupoCriteriosRubricasVO grupo_criterio = new GrupoCriteriosRubricasVO();
		List<GruposCriteriosRubricaAsignaturaVO> grupos_criterios_rubrica_asignatura;
	    
	    //if(!StringUtil.isNullOrBlank(f.geIdAsignatura())){
	    	grupo_criterio.setIdAsignatura(f.getIdAsignatura());
			grupos_criterios_rubrica_asignatura = getGruposCriteriosRubricasService().findGruposCriteriosRubricaAsignatura(grupo_criterio);

			//application/json or application/xml text/html
			response.setContentType("text/xml;charset=utf-8");
			PrintWriter out;
		    out = response.getWriter();
			out.print(parseXML(grupos_criterios_rubrica_asignatura));
			
			out.flush();
			out.close();
			return mapping.findForward("success");
			
		//}
	}
	
	public String parseXML(Object o) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("<grupos_criterios>");
		if (o != null){
			List<GruposCriteriosRubricaAsignaturaVO> grupos_criterios_rubrica_asignatura = (List<GruposCriteriosRubricaAsignaturaVO>) o;
			Iterator<GruposCriteriosRubricaAsignaturaVO> it = grupos_criterios_rubrica_asignatura.iterator();
			String id_grupo_criterios_anterior = null;
			GruposCriteriosRubricaAsignaturaVO gcra = null;
			
			if(it.hasNext()){
				gcra = it.next();
				id_grupo_criterios_anterior = gcra.getIdGrupoCriterio();
				sb.append("<grupo>");
				sb.append("<id_grupo><![CDATA[" + gcra.getIdGrupoCriterio() + "]]></id_grupo>");
				sb.append("<nombre_grupo><![CDATA[" + gcra.getNombreGrupoCriterio() + "]]></nombre_grupo>");
				sb.append("<tipo_grupo><![CDATA[" + gcra.getTipo() + "]]></tipo_grupo>");
				sb.append("<criterios>");
				sb.append("<criterio>");
				sb.append("<id_criterio><![CDATA[" + gcra.getIdCriterio() + "]]></id_criterio>");
				sb.append("<nombre_criterio><![CDATA[" + gcra.getNombreCriterio() + "]]></nombre_criterio>");
				sb.append("</criterio>");
				
				while(it.hasNext()) {
					gcra = it.next();
					if(!id_grupo_criterios_anterior.equals(gcra.getIdGrupoCriterio())){
						id_grupo_criterios_anterior = gcra.getIdGrupoCriterio();
						sb.append("</criterios>");
						sb.append("</grupo>");
						sb.append("<grupo>");
						sb.append("<id_grupo><![CDATA[" + gcra.getIdGrupoCriterio() + "]]></id_grupo>");
						sb.append("<nombre_grupo><![CDATA[" + gcra.getNombreGrupoCriterio() + "]]></nombre_grupo>");
						sb.append("<tipo_grupo><![CDATA[" + gcra.getTipo() + "]]></tipo_grupo>");
						sb.append("<criterios>");
					}
					sb.append("<criterio>");
					sb.append("<id_criterio><![CDATA[" + gcra.getIdCriterio() + "]]></id_criterio>");
					sb.append("<nombre_criterio><![CDATA[" + gcra.getNombreCriterio() + "]]></nombre_criterio>");
					sb.append("</criterio>");
				}
				
				sb.append("</criterios>");
				sb.append("</grupo>");
			}
		}
		sb.append("</grupos_criterios>");
		return sb.toString();
	}

}
