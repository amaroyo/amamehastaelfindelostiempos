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

public class GruposAnexoAsignaturaAction extends MrmAction {

	@Override
	public ActionForward process(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		EditarAsignaturaForm f = (EditarAsignaturaForm) form;
		GrupoCriteriosRubricasVO grupo_criterio = new GrupoCriteriosRubricasVO();
		List<GrupoCriteriosRubricasVO> grupos_anexo_asignatura;
	    
	    //if(!StringUtil.isNullOrBlank(f.geIdAsignatura())){
	    	grupo_criterio.setIdAsignatura(f.getIdAsignatura());
	    	grupos_anexo_asignatura = getGruposCriteriosRubricasService().findGruposAnexoAsignatura(grupo_criterio);
	    			
			//application/json or application/xml text/html
			response.setContentType("text/xml;charset=utf-8");
			PrintWriter out;
		    out = response.getWriter();
			out.print(parseXML(grupos_anexo_asignatura));
			
			out.flush();
			out.close();
			return mapping.findForward("success");
			
		//}
	}
	
	public String parseXML(Object o) throws Exception {
		
		StringBuffer sb = new StringBuffer();
		sb.append("<grupos_anexo>");
		if (o != null){
			List<GrupoCriteriosRubricasVO> grupos_anexo_asignatura = (List<GrupoCriteriosRubricasVO>) o;
			Iterator<GrupoCriteriosRubricasVO> it = grupos_anexo_asignatura.iterator();
			GrupoCriteriosRubricasVO gcra = null;
			
			while(it.hasNext()){
				gcra = it.next();
				sb.append("<grupo>");
				sb.append("<id_grupo><![CDATA[" + gcra.getIdGrupoCriterio() + "]]></id_grupo>");
				sb.append("<nombre_grupo><![CDATA[" + gcra.getNombre() + "]]></nombre_grupo>");
				sb.append("<tipo_grupo><![CDATA[" + gcra.getTipo() + "]]></tipo_grupo>");
				sb.append("</grupo>");
			}
		}
		sb.append("</grupos_anexo>");
		return sb.toString();
	}

}
