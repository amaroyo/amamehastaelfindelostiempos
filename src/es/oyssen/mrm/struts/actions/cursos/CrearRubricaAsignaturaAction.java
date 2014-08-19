package es.oyssen.mrm.struts.actions.cursos;

import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
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
		GrupoCriteriosRubricasVO grupo = new GrupoCriteriosRubricasVO();
		CriterioRubricaVO criterio = new CriterioRubricaVO();
		String tipo = "";
		int numero_criterios = 0;
		
		//application/json or application/xml text/html
		response.setContentType("text/xml;charset=utf-8");
		PrintWriter out;
	    out = response.getWriter();
	    
	    asignaturaIN.setNombre(f.getNombre());
		AsignaturaVO asignaturaOUT = getAsignaturasService().findByNombre(asignaturaIN);
				
		if (asignaturaOUT != null){
			
			String id_asignatura = asignaturaOUT.getIdAsignatura();
			
	        Map dynformValues = f.getValues();
	        
			// grupos_rubrica: "nota_grupo_1" / "texto_grupo_1" (tipo_grupo_#grupo)
	        Iterator it = dynformValues.entrySet().iterator();
	        while (it.hasNext()) {
	        	Map.Entry e = (Map.Entry)it.next();
	        	String key = (String) e.getKey();
	        	String value = (String) e.getValue();
	        	
	        	if(key.contains("grupo")){
	        		grupo = new GrupoCriteriosRubricasVO();
	        		grupo.setIdAsignatura(id_asignatura);
	        		grupo.setNombre(value);
	        		if(key.contains("nota")){
	        			tipo = "NOTA";
	        			numero_criterios = numero_criterios+1;
	        		}
	        		else if(key.contains("texto")){
	        			tipo = "TEXTO";
	        		}
	        		grupo.setTipo(tipo);
	        		getGruposCriteriosRubricasService().insert(grupo);
	        	}
	        }
	        
	        // criterios_rubrica: "nota_criterio_1_1" / "texto_criterio_1_1" (tipo_criterio_#grupo_#criterio)
	        it = dynformValues.entrySet().iterator();
	        while (it.hasNext()) {
	        	Map.Entry e = (Map.Entry)it.next();
	        	String key = (String) e.getKey();
	        	String value = (String) e.getValue();
	        	
	        	if(key.contains("criterio")){
	        		
	        		grupo = new GrupoCriteriosRubricasVO();
	        		grupo.setIdAsignatura(id_asignatura);
	        		String[] parts = key.split("_");
	        		tipo = parts[0];
	        		String grupo_asociado = parts[parts.length - 2];
	        		grupo.setNombre((String)dynformValues.get(tipo+"_grupo_"+grupo_asociado));
	        		grupo.setTipo(tipo.toUpperCase());
	        		grupo = getGruposCriteriosRubricasService().findByAsignaturaNombreTipo(grupo).get(0);
	        		
	        		criterio.setIdAsignatura(id_asignatura);
	        		criterio.setNombre(value);
	        		criterio.setIdGrupoCriterio(grupo.getIdGrupoCriterio());
	        		
	        		getCriteriosRubricasService().insert(criterio);
	        	}
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
