package es.oyssen.mrm.struts.actions.cursos;

import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import es.oyssen.mrm.negocio.vo.AsignaturaVO;
import es.oyssen.mrm.negocio.vo.CriterioRubricaVO;
import es.oyssen.mrm.negocio.vo.GrupoCriteriosRubricasVO;
import es.oyssen.mrm.negocio.vo.RubricaVO;
import es.oyssen.mrm.struts.actions.MrmAction;
import es.oyssen.mrm.struts.forms.cursos.CrearRubricaAsignaturaForm;

public class CrearRubricaAsignaturaAction extends MrmAction {

	@Override
	public ActionForward process(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		CrearRubricaAsignaturaForm f = (CrearRubricaAsignaturaForm) form;
		AsignaturaVO asignaturaIN = new AsignaturaVO();
		
		
		//application/json or application/xml text/html
		response.setContentType("text/xml;charset=utf-8");
		PrintWriter out;
	    out = response.getWriter();
	    
	    asignaturaIN.setNombre(f.getNombre());
		AsignaturaVO asignaturaOUT = getAsignaturasService().findByNombre(asignaturaIN);
				
		if (asignaturaOUT != null){
			
			String id_asignatura = asignaturaOUT.getIdAsignatura();
			GrupoCriteriosRubricasVO grupo = new GrupoCriteriosRubricasVO();
			CriterioRubricaVO criterio = new CriterioRubricaVO();
			int numero_criterios = 0;
			int i = 1;
			int j = 1;
			String value = null;
	        Map dynformValues = f.getValues();
	        
			// grupos_rubrica: "nota_grupo_1" (tipo_grupo_#grupo)
	        while ((value = (String) dynformValues.get("nota_grupo_"+i)) != null){
        		grupo = new GrupoCriteriosRubricasVO();
        		grupo.setIdAsignatura(id_asignatura);
        		grupo.setNombre(value);
        		numero_criterios = numero_criterios+1;
        		grupo.setTipo("NOTA");
        		getGruposCriteriosRubricasService().insert(grupo);
        		i = i + 1;
	        }
	        
	        i = 1;
			// grupos_rubrica: "texto_grupo_1" (tipo_grupo_#grupo)
	        while ((value = (String) dynformValues.get("texto_grupo_"+i)) != null){
        		grupo = new GrupoCriteriosRubricasVO();
        		grupo.setIdAsignatura(id_asignatura);
        		grupo.setNombre(value);
        		grupo.setTipo("TEXTO");
        		getGruposCriteriosRubricasService().insert(grupo);
        		i = i + 1;
	        }
	        
	        i = 1;
	        j = 1;
	        // criterios_rubrica: "nota_criterio_1_1" / "texto_criterio_1_1" (tipo_criterio_#grupo_#criterio)
	        while ((value = (String) dynformValues.get("nota_criterio_"+i+"_"+j)) != null){
	        	grupo = new GrupoCriteriosRubricasVO();
        		grupo.setIdAsignatura(id_asignatura);
        		grupo.setNombre((String)dynformValues.get("nota_grupo_"+i));
        		grupo.setTipo("NOTA");
        		grupo = getGruposCriteriosRubricasService().findByAsignaturaNombreTipo(grupo).get(0);
        		criterio.setIdAsignatura(id_asignatura);
        		criterio.setNombre(value);
        		criterio.setIdGrupoCriterio(grupo.getIdGrupoCriterio());
        		getCriteriosRubricasService().insert(criterio);
        		j = j + 1;
        		
		        while ((value = (String) dynformValues.get("nota_criterio_"+i+"_"+j)) != null){
	        		criterio.setNombre(value);
	        		criterio.setIdGrupoCriterio(grupo.getIdGrupoCriterio());
	        		getCriteriosRubricasService().insert(criterio);
	        		j = j + 1;
		        }
		        i = i + 1;
		        j = 1;
	        }
	        
	        i = 1;
	        j = 1;
	        // criterios_rubrica: "nota_criterio_1_1" / "texto_criterio_1_1" (tipo_criterio_#grupo_#criterio)
	        while ((value = (String) dynformValues.get("texto_criterio_"+i+"_"+j)) != null){
	        	grupo = new GrupoCriteriosRubricasVO();
        		grupo.setIdAsignatura(id_asignatura);
        		grupo.setNombre((String)dynformValues.get("texto_grupo_"+i));
        		grupo.setTipo("TEXTO");
        		grupo = getGruposCriteriosRubricasService().findByAsignaturaNombreTipo(grupo).get(0);
        		criterio.setIdAsignatura(id_asignatura);
        		criterio.setNombre(value);
        		criterio.setIdGrupoCriterio(grupo.getIdGrupoCriterio());
        		getCriteriosRubricasService().insert(criterio);
        		j = j + 1;
        		
		        while ((value = (String) dynformValues.get("texto_criterio_"+i+"_"+j)) != null){
	        		criterio.setNombre(value);
	        		criterio.setIdGrupoCriterio(grupo.getIdGrupoCriterio());
	        		getCriteriosRubricasService().insert(criterio);
	        		j = j + 1;
		        }
		        i = i + 1;
	        	j = 1;
	        }
	        	
	        

			// rubrica
			RubricaVO rubrica = new RubricaVO();
			rubrica.setIdAsignatura(id_asignatura);
			rubrica.setCompetencias(f.getCompetencias());
			rubrica.setAnexo(f.getAnexo1());
			rubrica.setNumeroCriterios(((Integer)numero_criterios).toString());
			getRubricasService().insert(rubrica);
			
			out.print("rubrica creada con éxito");
		}
		
		else {
			out.print("rúbrica no creada: no existe una asignatura con ese nombre");
		}	
		out.flush();
		out.close();
		return mapping.findForward("success");
	}
}
